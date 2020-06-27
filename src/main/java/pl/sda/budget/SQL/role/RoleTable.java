package pl.sda.budget.SQL.role;
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
public class RoleTable implements SQLOperations{

    private Connection conn;

    private final String CREATE_TYPE_OF_ROLES_SQL = "CREATE TABLE IF NOT EXISTS TYPE_OF_ROLES(" +
            "ID int(11) NOT NULL AUTO_INCREMENT," +
            "ROLES varchar(20) NOT NULL," +
            "PRIMARY KEY (ID));";

    private final String DROP_TYPE_OF_ROLES_SQL = "DROP TABLE TYPE_OF_ROLES ";

    @Override
    public void createTable() {
        try {
            conn.createStatement().execute(CREATE_TYPE_OF_ROLES_SQL);
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        try {
            conn.createStatement().execute(DROP_TYPE_OF_ROLES_SQL);
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}
