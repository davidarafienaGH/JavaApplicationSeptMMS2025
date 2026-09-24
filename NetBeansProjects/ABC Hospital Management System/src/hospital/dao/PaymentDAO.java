package hospital.dao;

import hospital.models.Invoice;
import hospital.models.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private final Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    
    public Payment create(Payment payment) throws SQLException {
        String sql = "INSERT INTO Payment (invoice_id, amount, paymentDate, paymentMethod) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, payment.getInvoice().getId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            stmt.setString(4, payment.getPaymentMethod());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating payment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    return findById(generatedId);
                } else {
                    throw new SQLException("Creating payment failed, no ID obtained.");
                }
            }
        }
    }

    public Payment findById(int id) throws SQLException {
        String sql = "SELECT p.id AS payment_id, p.amount, p.paymentDate, p.paymentMethod, " +
                     "i.id AS invoice_id, i.patient_id, i.total_amount, i.status, i.created_date " +
                     "FROM Payment p " +
                     "INNER JOIN Invoice i ON p.invoice_id = i.id " +
                     "WHERE p.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPayment(rs);
                }
            }
        }
        return null;
    }

    public List<Payment> findAll() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT p.id AS payment_id, p.amount, p.paymentDate, p.paymentMethod, " +
                     "i.id AS invoice_id, i.patient_id, i.total_amount, i.status, i.created_date " +
                     "FROM Payment p " +
                     "INNER JOIN Invoice i ON p.invoice_id = i.id " +
                     "ORDER BY p.paymentDate DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                payments.add(mapResultSetToPayment(rs));
            }
        }
        return payments;
    }

    public List<Payment> findByInvoiceId(int invoiceId) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT p.id AS payment_id, p.amount, p.paymentDate, p.paymentMethod, " +
                     "i.id AS invoice_id, i.patient_id, i.total_amount, i.status, i.created_date " +
                     "FROM Payment p " +
                     "INNER JOIN Invoice i ON p.invoice_id = i.id " +
                     "WHERE p.invoice_id = ? " +
                     "ORDER BY p.paymentDate DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, invoiceId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    payments.add(mapResultSetToPayment(rs));
                }
            }
        }
        return payments;
    }

    public boolean update(Payment payment) throws SQLException {
        String sql = "UPDATE Payment SET invoice_id = ?, amount = ?, paymentDate = ?, paymentMethod = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, payment.getInvoice().getId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            stmt.setString(4, payment.getPaymentMethod());
            stmt.setInt(5, payment.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM Payment WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Payment mapResultSetToPayment(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(rs.getInt("invoice_id"));
        invoice.setId(rs.getInt("patient_id"));
        invoice.setTotalAmount(rs.getDouble("total_amount"));
        invoice.setStatus(rs.getString("status"));
        Date createdDate = rs.getDate("created_date");
        if (createdDate != null) {
            invoice.setInvoiceDate(createdDate.toLocalDate());
        }

        Payment payment = new Payment();
        // Uses reflection/bytecode structure corresponding to your Payment.class
        try {
            java.lang.reflect.Field idField = Payment.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(payment, rs.getInt("payment_id"));
        } catch (Exception e) {
            throw new SQLException("Error mapping payment ID", e);
        }

        payment.setInvoice(invoice);
        payment.setAmount(rs.getDouble("amount"));
        payment.setPaymentMethod(rs.getString("paymentMethod"));

        Date payDate = rs.getDate("paymentDate");
        if (payDate != null) {
            payment.setPaymentDate(payDate.toLocalDate());
        }

        return payment;
    }
}