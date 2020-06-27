package pl.sda.budget.SQL.invoice;

import lombok.*;
import pl.sda.budget.SQL.dbutils.SQLOperations;

import java.sql.Connection;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceTable implements SQLOperations {

    private Connection conn;

    private final String CREATE_INVOICE_SQL = "CREATE TABLE IF NOT EXISTS INVOICE(" +
            "ID int(11) NOT NULL AUTO_INCREMENT," +
            "INVOICE_NUMBER varchar(20) NOT NULL," +
            "INVOICE_DATE DATE() NOT NULL," +
            "DATE_OF_PAYMENT DATE() NOT NULL," +
            "AMOUNT DECIMAL(20,2) NOT NULL," +
            "CONTRACTOR varchar(20) NOT NULL," +
            "AMOUNT_IN_EUR DECIMAL(20,2) NOT NULL," +
            "ID_TYPE_OF_COSTS int(11) NOT NULL," +
            "ID_CURRENCY int(11) NOT NULL," +
            "PRIMARY KEY (ID));";

    private final String DROP_INVOICE_SQL = "DROP TABLE INVOICE ";

    @Override
    public void createTable() {
        try {
            conn.createStatement().execute(CREATE_INVOICE_SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void dropTable() {
        try {
            conn.createStatement().execute(DROP_INVOICE_SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
