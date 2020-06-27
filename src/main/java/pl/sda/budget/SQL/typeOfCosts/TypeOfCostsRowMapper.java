package pl.sda.budget.SQL.typeOfCosts;

import pl.sda.budget.dialogs.DialogUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeOfCostsRowMapper {

    public static TypeOfCosts map(ResultSet rs, int rn) {
        try {
            return TypeOfCosts.TypeOfCostsBuilder.builder()
                    .id(rs.getInt("ID"))
                    .typeOfCosts(rs.getString("TYPE_OF_COSTS"))
                    .build();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }


    public static List<TypeOfCosts> map(ResultSet rs) {
        List<TypeOfCosts> costs = new ArrayList<>();

        while (true) {
            try {
                if (!rs.next()) break;
            } catch (Exception e) {
                DialogUtils.errorDialog(e.getMessage());
            }
            try {
                costs.add(map(rs, rs.getRow()));
            } catch (Exception e) {
                DialogUtils.errorDialog(e.getMessage());
            }

        }
        return costs;
    }
}
