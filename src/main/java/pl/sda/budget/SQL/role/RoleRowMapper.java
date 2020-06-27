package pl.sda.budget.SQL.role;

import pl.sda.budget.SQL.typeOfCosts.TypeOfCosts;
import pl.sda.budget.dialogs.DialogUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRowMapper {

    public static Role map(ResultSet rs, int rn) {
        try {
            return Role.RoleBuilder.builder()
                    .id(rs.getInt("ID"))
                    .roleName(rs.getString("ROLES"))
                    .build();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    public static List<Role> map(ResultSet rs) {
        List<Role> roles = new ArrayList<>();

        while (true) {
            try {
                if (!rs.next()) break;
                    roles.add(map(rs, rs.getRow()));
            } catch (Exception e) {
                DialogUtils.errorDialog(e.getMessage());
            }

            }
            return roles;
        }

}