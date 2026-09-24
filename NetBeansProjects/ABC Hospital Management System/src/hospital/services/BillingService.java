package hospital.services;

import hospital.models.Invoice;
import hospital.models.InvoiceItem;
import hospital.models.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BillingService {
    private final List<Invoice> invoiceList = new ArrayList<>();
    private final List<InvoiceItem> itemList = new ArrayList<>();
    private final List<Payment> paymentList = new ArrayList<>();
    
    private int nextInvoiceId = 1;
    private int nextItemId = 1;
    private int nextPaymentId = 1;

    public boolean createInvoice(Invoice invoice) {
        invoice.setId(nextInvoiceId++);
        return invoiceList.add(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceList;
    }

    public Invoice getInvoiceById(int id) {
        return invoiceList.stream()
                .filter(inv -> inv.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean updateInvoice(Invoice invoice) {
        for (int i = 0; i < invoiceList.size(); i++) {
            if (invoiceList.get(i).getId() == invoice.getId()) {
                invoiceList.set(i, invoice);
                return true;
            }
        }
        return false;
    }

    public boolean addInvoiceItem(InvoiceItem item) {
        item.setId(nextItemId++);
        return itemList.add(item);
    }

    public List<InvoiceItem> getInvoiceItemsByInvoiceId(int invoiceId) {
        return itemList.stream()
                .filter(item -> item.getId() == invoiceId)
                .collect(Collectors.toList());
    }

    public boolean recordPayment(Payment payment) {
        payment.setId(nextPaymentId++);
        return paymentList.add(payment);
    }

    public List<Payment> getPaymentsByInvoiceId(int invoiceId) {
        return paymentList.stream()
                .filter(p -> p.getInvoice() != null && p.getInvoice().getId() == invoiceId)
                .collect(Collectors.toList());
    }

    public double getTotalPaidForInvoice(int invoiceId) {
        return paymentList.stream()
                .filter(p -> p.getInvoice() != null && p.getInvoice().getId() == invoiceId)
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    public List<Invoice> getInvoicesByPatientId(int patientId) {
        return invoiceList.stream()
                .filter(inv -> inv.getPatient() != null && inv.getPatient().getPatientId() == patientId)
                .collect(Collectors.toList());
    }

    public List<Invoice> getOutstandingInvoices() {
        return invoiceList.stream()
                .filter(inv -> !"Paid".equalsIgnoreCase(inv.getStatus()))
                .collect(Collectors.toList());
    }
}