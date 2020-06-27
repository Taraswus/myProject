package pl.sda.budget.SQL.role;

import lombok.Getter;
import lombok.Setter;
import pl.sda.budget.SQL.dbutils.ConnectionUtils;
import pl.sda.budget.SQL.dbutils.ObjectRepository;
import pl.sda.budget.dialogs.DialogUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class RoleRepository implements ObjectRepository<Role> {
    private final String GET_ALL_SQL = "SELECT * FROM TYPE_OF_ROLES";
    private final String FIND_ONE_BY_ID_SQL = "SELECT * FROM TYPE_OF_ROLES WHERE id = ?";
    private final String SAVE_SQL = "INSERT INTO TYPE_OF_ROLES (ROLES) VALUES (?)";
    private final String UPDATE_SQL = "UPDATE TYPE_OF_ROLES SET ROLES = ?  WHERE id=?";
    private final String DELETE_SQL = "DELETE TYPE_OF_ROLES FROM TYPE_OF_ROLES WHERE id=?";

    private Connection conn;

    public RoleRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Role> getAll() {

        try {
            return RoleRowMapper.map(conn.prepareStatement(GET_ALL_SQL).executeQuery());
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Role> findOneById(int id) {


        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(FIND_ONE_BY_ID_SQL);
            preparedStatement.setInt(1, id);


            ResultSet rs = preparedStatement.executeQuery();


            return rs.next() ?
                    Optional.of(RoleRowMapper.map(rs, rs.getRow())) :
                    Optional.empty();

        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        return null;



    }

    @Override
    public Role save(Role role) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(
                    SAVE_SQL,
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, role.getRoleName());
            if (preparedStatement.executeUpdate() == 1) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    return Role.RoleBuilder.builder(role)
                            .id(rs.getInt(1))
                            .build();
                }
            }
            throw new SQLException("Cannot perform save");

        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Optional<Role> role) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(UPDATE_SQL);
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        try {
            preparedStatement.setString(1, role.get().getRoleName());
            preparedStatement.setInt(2, role.get().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    @Override
    public List<Role> save(List<Role> role) {
        List<Role> saveRoles = new ArrayList<>();

        ConnectionUtils.disableAutoCommit(conn);

        try {
            for (Role role1 : role) {
                saveRoles.add(save(role1));
            }
        } catch (Exception e) {
            ConnectionUtils.rollback(conn);
            DialogUtils.errorDialog(e.getMessage());
        } finally {
            ConnectionUtils.enableAutoCommit(conn);

        }
        return saveRoles;
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