package pl.sda.budget.SQL.user;

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
public class UserTable implements SQLOperations {

    private Connection conn;

    private final String CREATE_USER_SQL = "CREATE TABLE IF NOT EXISTS USER(" +
            "ID int(11) NOT NULL AUTO_INCREMENT," +
            "USER_NAME varchar(20) NOT NULL UNIQUE ," +
            "PASSWORD varchar(20)," +
            "ROLE varchar (20)," +
//            "CONSTRAINT USER_ROLE FOREIGN KEY (ROLE) references TYPE_OF_ROLES(ROLES)," +
            "PRIMARY KEY (ID));";


    private final String DROP_USER_SQL = "DROP TABLE USER ";

    @Override
    public void createTable() {
        try {
            conn.createStatement().execute(CREATE_USER_SQL);
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        try {
            conn.createStatement().execute(DROP_USER_SQL);
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}
