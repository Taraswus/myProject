package pl.sda.budget.SQL.invoice;

import pl.sda.budget.SQL.typeOfCosts.TypeOfCosts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceRowMapper {

    public static Invoice map (ResultSet rs,int rn) throws SQLException {
        return Invoice.InvoiceBuilder.builder()
                .id(rs.getInt("ID"))
                .documentNumber(rs.getString("INVOICE_NUMBER"))
                .documentDate(rs.getDate("INVOICE_DATE"))
                .dateOfPayment(rs.getDate("DATE_OF_PAYMENT"))
                .amount(rs.getBigDecimal("AMOUNT"))
                .contractor(rs.getString("CONTRACTOR"))
                .amountInEUR(rs.getBigDecimal("AMOUNT_IN_EUR"))
                .idTypeOfCosts(rs.getInt("ID_TYPE_OF_COSTS"))
                .idCurrency(rs.getInt("ID_CURRENCY"))
                .build();

    }
    public static List<Invoice> map(ResultSet rs) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();

        while (rs.next()) {
            invoices.add(map(rs, rs.getRow()));
        }

        return invoices;
    }
}

