package pl.sda.budget.SQL.invoice;

import pl.sda.budget.SQL.dbutils.ConnectionUtils;
import pl.sda.budget.SQL.dbutils.ObjectRepository;
import pl.sda.budget.SQL.role.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceRepository implements ObjectRepository<Invoice> {

    private final String GET_ALL_SQL = "SELECT * FROM INVOICE";
    private final String FIND_ONE_BY_ID_SQL = "SELECT * FROM INVOICE WHERE id = ?";
    private final String SAVE_SQL = "INSERT INTO INVOICE (INVOICE_NUMBER, INVOICE_DATE, DATE_OF_PAYMENT, AMOUNT," +
            "CONTRACTOR, AMOUNT_IN_EUR,ID_TYPE_OF_COSTS,ID_CURRENCY) VALUES (?,?,?,?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE INVOICE SET INVOICE_NUMBER =?,INVOICE_DATE=?,DATE_OF_PAYMENT=?," +
            "AMOUNT=?, CONTRACTOR=?,AMOUNT_IN_EUR=?,ID_TYPE_OF_COSTS=?,ID_CURRENCY=? WHERE id=?";
    private final String DELETE_SQL = "DELETE INVOICE  WHERE id=?";

    private Connection conn;

    public InvoiceRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List getAll() throws SQLException {
        return InvoiceRowMapper.map(conn.prepareStatement(GET_ALL_SQL).executeQuery());
    }

    @Override
    public Optional findOneById(int id) {
        return Optional.empty();
    }

    @Override
    public Invoice save(Invoice invoice) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(
                SAVE_SQL,
                Statement.RETURN_GENERATED_KEYS
        );


        preparedStatement.setString(1, invoice.getDocumentNumber());
        preparedStatement.setDate(2, (Date) invoice.getDocumentDate());
        preparedStatement.setDate(3, (Date) invoice.getDateOfPayment());
        preparedStatement.setBigDecimal(4, invoice.getAmount());
        preparedStatement.setString(5,invoice.getContractor());
        preparedStatement.setBigDecimal(6,invoice.getAmountInEUR());
        preparedStatement.setInt(7,invoice.getIdTypeOfCosts());
        preparedStatement.setInt(8,invoice.getIdCurrency());

        if (preparedStatement.executeUpdate() == 1) {
            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                return Invoice.InvoiceBuilder.builder(invoice)
                        .id(rs.getInt(1))
                        .build();

            }
        }
        throw new SQLException("Cannot perform save");
    }

    @Override
    public void update(Optional<Invoice> invoice) {

    }



    @Override
    public List<Invoice> save (List <Invoice>invoices) {
        List<Invoice> saveInvoice = new ArrayList<>();

        ConnectionUtils.disableAutoCommit(conn);

        try {
            for (Invoice invoice : invoices) {
                saveInvoice.add(save(invoice));
            }
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.enableAutoCommit(conn);
        }
        return saveInvoice;
    }

    @Override
    public void delete( int id) {

    }
}
