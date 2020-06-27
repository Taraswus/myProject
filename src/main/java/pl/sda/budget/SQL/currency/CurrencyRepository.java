package pl.sda.budget.SQL.currency;
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
public class CurrencyRepository implements ObjectRepository<Currency> {
    private final String GET_ALL_SQL = "SELECT * FROM CURRENCIES";
    private final String FIND_ONE_BY_ID_SQL = "SELECT * FROM CURRENCIES WHERE id = ?";
    private final String SAVE_SQL = "INSERT INTO CURRENCIES (CURRENCIES) VALUES (?)";
    private final String UPDATE_SQL = "UPDATE CURRENCIES SET CURRENCIES =?  WHERE id=?";
    private final String DELETE_SQL = "DELETE CURRENCIES FROM CURRENCIES WHERE id=?";

    private Connection conn;

    public CurrencyRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Currency> getAll() {
        try {
            return CurrencyRowMapper.map(conn.prepareStatement(GET_ALL_SQL).executeQuery());
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Currency> findOneById(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(FIND_ONE_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            return rs.next() ?
                    Optional.of(CurrencyRowMapper.map(rs, rs.getRow())) :
                    Optional.empty();

        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        return null;
    }

    @Override
    public Currency save(Currency currency) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(
                    SAVE_SQL,
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, currency.getCurrencyName());
            if (preparedStatement.executeUpdate() == 1) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    return Currency.CurrencyBuilder.builder(currency)
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
        public void update (Optional<Currency> currency) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = conn.prepareStatement(UPDATE_SQL);
            } catch (Exception e) {
                DialogUtils.errorDialog(e.getMessage());
            }

            try {
                preparedStatement.setString(1, currency.get().getCurrencyName());
                preparedStatement.setInt(2, currency.get().getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (Exception e) {
                DialogUtils.errorDialog(e.getMessage());
            }
        }


        @Override
        public List<Currency> save (List < Currency > currency) {
            List<Currency> saveCurrencies = new ArrayList<>();

            ConnectionUtils.disableAutoCommit(conn);

            try {
                for (Currency currency1 : currency) {
                    saveCurrencies.add(save(currency1));
                }
            } catch (Exception e) {
                ConnectionUtils.rollback(conn);
                DialogUtils.errorDialog(e.getMessage());
            } finally {
                ConnectionUtils.enableAutoCommit(conn);
            }
            return saveCurrencies;
        }

        @Override
        public void delete (int id){

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

