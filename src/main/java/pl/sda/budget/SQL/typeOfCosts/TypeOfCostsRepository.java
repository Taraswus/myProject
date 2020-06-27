package pl.sda.budget.SQL.typeOfCosts;

import lombok.Getter;
import lombok.Setter;
import pl.sda.budget.SQL.dbutils.ConnectionUtils;
import pl.sda.budget.SQL.dbutils.ObjectRepository;
import pl.sda.budget.SQL.role.Role;
import pl.sda.budget.dialogs.DialogUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Setter
@Getter

public class TypeOfCostsRepository implements ObjectRepository<TypeOfCosts> {
    private final String GET_ALL_SQL = "SELECT * FROM TYPE_OF_COSTS";
    private final String FIND_ONE_BY_ID_SQL = "SELECT * FROM TYPE_OF_COSTS WHERE id = ?";
    private final String SAVE_SQL = "INSERT INTO TYPE_OF_COSTS (TYPE_OF_COSTS) VALUES (?)";
    private final String UPDATE_SQL = "UPDATE TYPE_OF_COSTS SET TYPE_OF_COSTS =?  WHERE id=?";
    private final String DELETE_SQL = "DELETE TYPE_OF_COSTS FROM TYPE_OF_COSTS WHERE id=?";

    private Connection conn;

    public TypeOfCostsRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<TypeOfCosts> getAll()  {
        try {
        return TypeOfCostsRowMapper.map(conn.prepareStatement(GET_ALL_SQL).executeQuery());
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<TypeOfCosts> findOneById(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(FIND_ONE_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            return rs.next() ?
                    Optional.of(TypeOfCostsRowMapper.map(rs, rs.getRow())) :
                    Optional.empty();

        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        return null;
    }

    @Override
    public TypeOfCosts save(TypeOfCosts typeOfCosts)  {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(
                SAVE_SQL,
                Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1, typeOfCosts.getTypeOfCosts());
        if (preparedStatement.executeUpdate() == 1) {
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return TypeOfCosts.TypeOfCostsBuilder.builder(typeOfCosts)
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
    public void update(Optional<TypeOfCosts> cost) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(UPDATE_SQL);
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        try {
            preparedStatement.setString(1, cost.get().getTypeOfCosts());
            preparedStatement.setInt(2, cost.get().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    @Override
    public List<TypeOfCosts> save(List<TypeOfCosts> typeOfCosts) {
        List<TypeOfCosts> saveCosts = new ArrayList<>();

        ConnectionUtils.disableAutoCommit(conn);

        try {
            for (TypeOfCosts cost : typeOfCosts) {
                saveCosts.add(save(cost));
            }
        } catch (Exception e) {
            ConnectionUtils.rollback(conn);
            DialogUtils.errorDialog(e.getMessage());
        } finally {
            ConnectionUtils.enableAutoCommit(conn);
        }
        return saveCosts;
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
