package pl.sda.budget.SQL.user;

import pl.sda.budget.dialogs.DialogUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserRowMapper {
    public static User map(ResultSet rs, int rn)  {
        try {
            return User.UserBuilder.builder()
                    .id(rs.getInt("ID"))
                    .userName(rs.getString("USER_NAME"))
                    .password(rs.getString("PASSWORD"))
                    .role(rs.getString("ROLE"))
                    .build();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    public static List<User> map(ResultSet rs)  {
        List<User> users = new ArrayList<>();

        while (true) {
            try {
                if (!rs.next()) break;
                users.add(map(rs, rs.getRow()));
            } catch (Exception e) {
                DialogUtils.errorDialog(e.getMessage());
            }

        }

        return users;
    }
}
