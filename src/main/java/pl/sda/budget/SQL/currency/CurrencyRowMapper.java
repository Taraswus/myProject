package pl.sda.budget.SQL.currency;
import pl.sda.budget.SQL.typeOfCosts.TypeOfCosts;
import pl.sda.budget.dialogs.DialogUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CurrencyRowMapper {
    public static Currency map(ResultSet rs, int rn){
        try {
        return Currency.CurrencyBuilder.builder()
                .id(rs.getInt("ID"))
                .currencyName(rs.getString("CURRENCIES"))
                .build();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }
    public static List<Currency> map(ResultSet rs)  {
        List<Currency> currencies = new ArrayList<>();

        while (true) {
            try {
                if (!rs.next()) break;
            } catch (Exception e) {
                DialogUtils.errorDialog(e.getMessage());
            }
            try {
                currencies.add(map(rs, rs.getRow()));
            } catch (Exception e) {
                DialogUtils.errorDialog(e.getMessage());
            }

        }
        return currencies;
    }
}