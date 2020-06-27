package pl.sda.budget.SQL.invoice;

import pl.sda.budget.SQL.role.Role;
import pl.sda.budget.SQL.role.RoleRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static pl.sda.budget.SQL.dbutils.ConnectionParameter.*;

public class InvoiceModel {
//
//    public void saveInvoice(String documentNumber
//            ,String documentDate
//            ,String dateOfPayment
//            ,String amount
//            ,String contractor
//            ,String amountInEUR
//            ,int idTypeOfCosts
//            ,int idCurrency) {
//        try {
//            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
//                InvoiceRepository invoiceRepository = new InvoiceRepository(conn);
//                invoiceRepository.getAll();
//                List<Invoice> invoices = Arrays.asList(
//                        Invoice.InvoiceBuilder.builder().documentNumber(documentNumber)
//                                .documentDate(documentDate)
//                                .dateOfPayment(dateOfPayment)
//                                .amount(amount)
//                                .contractor(contractor)
//                                .amountInEUR(amountInEUR)
//                                .idTypeOfCosts(idTypeOfCosts)
//                                .idCurrency(idCurrency)
//                                .build());
//
//                invoiceRepository.save(invoices);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}
