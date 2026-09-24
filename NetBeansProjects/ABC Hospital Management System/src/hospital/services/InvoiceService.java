package hospital.services;

import hospital.dao.InvoiceDAO;
import hospital.models.Invoice;
import hospital.models.Patient;
import java.sql.Connection;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class InvoiceService {

    private final InvoiceDAO invoiceDAO = new InvoiceDAO(connection);
    private static Connection connection;
    

    public Invoice createInvoice(Patient patient, LocalDate invoiceDate, String status) throws Exception {
        if (patient == null || patient.getPatientId() <= 0) {
            throw new IllegalArgumentException("A valid patient must be specified.");
        }
        if (status == null || status.trim().isEmpty()) {
            status = "Pending";
        }

        Invoice invoice = new Invoice();
        invoice.setPatient(patient);
        invoice.setInvoiceDate(invoiceDate != null ? invoiceDate : LocalDate.now());
        invoice.setTotalAmount(0.0);
        invoice.setStatus(status.trim());

        return invoiceDAO.create(invoice);
    }

    public Invoice getInvoiceById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid invoice ID.");
        }
        return invoiceDAO.findById(id);
    }

    public List<Invoice> getAllInvoices() throws SQLException {
        return invoiceDAO.findAll();
    }

    public List<Invoice> getInvoicesByPatientId(int patientId) throws SQLException {
        if (patientId <= 0) {
            throw new IllegalArgumentException("Invalid patient ID.");
        }
        return invoiceDAO.findByPatientId(patientId);
    }

    public boolean updateStatus(int invoiceId, String newStatus) throws SQLException {
        Invoice invoice = getInvoiceById(invoiceId);
        if (invoice == null) {
            return false;
        }
        invoice.setStatus(newStatus);
        return invoiceDAO.update(invoice);
    }

    public boolean deleteInvoice(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid invoice ID.");
        }
        return invoiceDAO.delete(id);
    }
}