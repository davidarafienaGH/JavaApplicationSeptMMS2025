package hospital.userview;

import hospital.models.Invoice;
import hospital.models.InvoiceItem;
import hospital.models.Payment;

import java.util.List;

public class BillingView {

    public void displayInvoices(List<Invoice> invoices) {
        System.out.printf("%-10s %-12s %-12s %-15s %-10s%n", "Invoice ID", "Patient ID", "Date", "Total Amount", "Status");
        System.out.println("------------------------------------------------------------------");
        for (Invoice inv : invoices) {
            int patientId = (inv.getPatient() != null) ? inv.getPatient().getPatientId() : 0;
            System.out.printf("%-10d %-12d %-12s $%-14.2f %-10s%n",
                    inv.getId(),
                    patientId,
                    inv.getInvoiceDate(),
                    inv.getTotalAmount(),
                    inv.getStatus());
        }
    }

    public void displayInvoice(Invoice inv) {
        System.out.println("\n--- Invoice Details ---");
        System.out.println("Invoice ID   : " + inv.getId());
        System.out.println("Patient ID   : " + ((inv.getPatient() != null) ? inv.getPatient().getPatientId() : "N/A"));
        System.out.println("Invoice Date : " + inv.getInvoiceDate());
        System.out.printf("Total Amount : $%.2f%n", inv.getTotalAmount());
        System.out.println("Status       : " + inv.getStatus());
    }

    public void displayInvoiceItems(List<InvoiceItem> items) {
        System.out.printf("%-10s %-12s %-30s %-10s%n", "Item ID", "Invoice ID", "Description", "Amount");
        System.out.println("------------------------------------------------------------------");
        for (InvoiceItem item : items) {
            System.out.printf("%-10d %-12d %-30s $%-9.2f%n",
                    item.getId(),
                    item.getId(),
                    item.getDescription(),
                    item.getAmount());
        }
    }

    public void displayPayments(List<Payment> payments) {
        System.out.printf("%-12s %-12s %-12s %-15s %-15s%n", "Payment ID", "Invoice ID", "Date", "Amount", "Method");
        System.out.println("------------------------------------------------------------------");
        for (Payment p : payments) {
            int invoiceId = (p.getInvoice() != null) ? p.getInvoice().getId() : 0;
            System.out.printf("%-12d %-12d %-12s $%-14.2f %-15s%n",
                    p.getId(),
                    invoiceId,
                    p.getPaymentDate(),
                    p.getAmount(),
                    p.getPaymentMethod());
        }
    }
}