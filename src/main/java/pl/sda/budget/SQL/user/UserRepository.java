package pl.sda.budget.SQL.user;


import pl.sda.budget.SQL.dbutils.ConnectionUtils;
import pl.sda.budget.SQL.dbutils.ObjectRepository;
import pl.sda.budget.SQL.role.Role;
import pl.sda.budget.SQL.role.RoleRowMapper;
import pl.sda.budget.dialogs.DialogUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements ObjectRepository<User> {
    private final String GET_ALL_SQL = "SELECT * FROM USER";
    private final String FIND_ONE_BY_ID_SQL = "SELECT * FROM USER WHERE id = ?";
    private final String SAVE_SQL = "INSERT INTO USER (USER_NAME, PASSWORD, ROLE) VALUES (?,?,?)";
    private final String UPDATE_SQL = "UPDATE USER SET USER_NAME=?, PASSWORD=?, ROLE=?  WHERE id=?";
    private final String DELETE_SQL = "DELETE USER FROM USER WHERE id=?";

    private Connection conn;

    public UserRepository(Connection conn) {
        this.conn = conn;
    }


    @Override
    public List<User> getAll() {
        try {
            return UserRowMapper.map(conn.prepareStatement(GET_ALL_SQL).executeQuery());
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<User> findOneById(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(FIND_ONE_BY_ID_SQL);
            preparedStatement.setInt(1, id);


            ResultSet rs = preparedStatement.executeQuery();


            return rs.next() ?
                    Optional.of(UserRowMapper.map(rs, rs.getRow())) :
                    Optional.empty();

        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        return null;


    }


    @Override
    public User save(User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(
                    SAVE_SQL,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());


            if (preparedStatement.executeUpdate() == 1) {
                ResultSet rs = preparedStatement.getGeneratedKeys();

                if (rs.next()) {
                    return User.UserBuilder.builder(user)
                            .id(rs.getInt(1))
                            .build();
                }
                throw new SQLException("Cannot perform save");
            }
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        return null;


    }

    @Override
    public void update(Optional<User> user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(UPDATE_SQL);
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        try {
            preparedStatement.setString(1, user.get().getUserName());
            preparedStatement.setString(2, user.get().getPassword());
            preparedStatement.setString(3, user.get().getRole());
            preparedStatement.setInt(4, user.get().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

    }


    @Override
    public List<User> save(List<User> user) {
        List<User> saveUsers = new ArrayList<>();

        ConnectionUtils.disableAutoCommit(conn);

        try {
            for (User users : user) {
                saveUsers.add(save(users));
            }
        } catch (Exception e) {
            ConnectionUtils.rollback(conn);
            DialogUtils.errorDialog(e.getMessage());
        } finally {
            ConnectionUtils.enableAutoCommit(conn);
        }
        return saveUsers;
    }


    @Override
    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(DELETE_SQL);
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}

