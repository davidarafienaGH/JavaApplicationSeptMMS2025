package hospital.services;

import hospital.dao.PaymentDAO;
import hospital.models.Invoice;
import hospital.models.Patient;
import hospital.models.Payment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public Payment processPayment(Invoice invoice, double amount, String paymentMethod, LocalDate paymentDate) throws Exception {
        if (invoice == null || invoice.getId() <= 0) {
            throw new IllegalArgumentException("A valid Invoice must be provided.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be empty.");
        }

        LocalDate finalDate = (paymentDate != null) ? paymentDate : LocalDate.now();

        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod.trim());
        payment.setPaymentDate(finalDate);

        return paymentDAO.create(payment);
    }

    public Payment getPaymentById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid payment ID.");
        }
        return paymentDAO.findById(id);
    }

    public List<Payment> getAllPayments() throws SQLException {
        return paymentDAO.findAll();
    }

    public List<Payment> getPaymentsByInvoiceId(int invoiceId) throws SQLException {
        if (invoiceId <= 0) {
            throw new IllegalArgumentException("Invalid invoice ID.");
        }
        return paymentDAO.findByInvoiceId(invoiceId);
    }

    public boolean updatePayment(Payment payment) throws Exception {
        if (payment == null || payment.getId() <= 0) {
            throw new IllegalArgumentException("Invalid payment record provided.");
        }
        if (payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
        return paymentDAO.update(payment);
    }

    public boolean voidPayment(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid payment ID.");
        }
        return paymentDAO.delete(id);
    }

}
    