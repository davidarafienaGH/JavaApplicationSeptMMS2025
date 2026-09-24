package hospital.dao;

import hospital.models.Invoice;
import hospital.models.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {

    private final Connection connection;

    public InvoiceDAO(Connection connection) {
        this.connection = connection;
    }

    public Invoice create(Invoice invoice) throws SQLException {
        String sql = "INSERT INTO Invoice (patient_id, invoiceDate, totalAmount, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, invoice.getPatient().getPatientId());
            stmt.setDate(2, Date.valueOf(invoice.getInvoiceDate()));
            stmt.setDouble(3, invoice.getTotalAmount());
            stmt.setString(4, invoice.getStatus());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating invoice failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return findById(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating invoice failed, no ID obtained.");
                }
            }
        }
    }

    public Invoice findById(int id) throws SQLException {
        String sql = "SELECT i.id, i.invoiceDate, i.totalAmount, i.status, " +
                     "p.PatientId, p.FirstName, p.LastName " +
                     "FROM Invoice i " +
                     "INNER JOIN Patient p ON i.patient_id = p.PatientId " +
                     "WHERE i.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToInvoice(rs);
                }
            }
        }
        return null;
    }

    public List<Invoice> findAll() throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT i.id, i.invoiceDate, i.totalAmount, i.status, " +
                     "p.PatientId, p.FirstName, p.LastName " +
                     "FROM Invoice i " +
                     "INNER JOIN Patient p ON i.patient_id = p.PatientId " +
                     "ORDER BY i.invoiceDate DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                invoices.add(mapResultSetToInvoice(rs));
            }
        }
        return invoices;
    }

    public List<Invoice> findByPatientId(int patientId) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT i.id, i.invoiceDate, i.totalAmount, i.status, " +
                     "p.PatientId, p.FirstName, p.LastName " +
                     "FROM Invoice i " +
                     "INNER JOIN Patient p ON i.patient_id = p.PatientId " +
                     "WHERE i.patient_id = ? " +
                     "ORDER BY i.invoiceDate DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    invoices.add(mapResultSetToInvoice(rs));
                }
            }
        }
        return invoices;
    }

    public boolean update(Invoice invoice) throws SQLException {
        String sql = "UPDATE Invoice SET patient_id = ?, invoiceDate = ?, totalAmount = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, invoice.getPatient().getPatientId());
            stmt.setDate(2, Date.valueOf(invoice.getInvoiceDate()));
            stmt.setDouble(3, invoice.getTotalAmount());
            stmt.setString(4, invoice.getStatus());
            stmt.setInt(5, invoice.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM Invoice WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Invoice mapResultSetToInvoice(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("PatientId"));

        Invoice invoice = new Invoice();
        try {
            java.lang.reflect.Field idField = Invoice.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(invoice, rs.getInt("id"));
        } catch (Exception e) {
            throw new SQLException("Error setting invoice ID", e);
        }

        invoice.setPatient(patient);
        invoice.setTotalAmount(rs.getDouble("totalAmount"));
        invoice.setStatus(rs.getString("status"));

        Date date = rs.getDate("invoiceDate");
        if (date != null) {
            invoice.setInvoiceDate(date.toLocalDate());
        }

        return invoice;
    }
}