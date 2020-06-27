package pl.sda.budget.SQL.currency;

import lombok.*;
import pl.sda.budget.SQL.dbutils.SQLOperations;
import pl.sda.budget.dialogs.DialogUtils;

import java.sql.Connection;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyTable implements SQLOperations {

    private Connection conn;

    private final String CREATE_CURRENCIES_SQL = "CREATE TABLE IF NOT EXISTS CURRENCIES(" +
            "ID int(11) NOT NULL AUTO_INCREMENT," +
            "CURRENCIES varchar(20) NOT NULL," +
            "PRIMARY KEY (ID));";

    private final String DROP_CURRENCIES_SQL = "DROP TABLE CURRENCIES ";

    @Override
    public void createTable() {
        try {
            conn.createStatement().execute(CREATE_CURRENCIES_SQL);
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        try {
            conn.createStatement().execute(DROP_CURRENCIES_SQL);
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}
