package hospital.userview;

import hospital.models.Invoice;
import hospital.models.Payment;
import hospital.services.PaymentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Scanner;

public class PaymentView {

    private final PaymentService paymentService;
    private final Scanner scanner;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public PaymentView(PaymentService paymentService) {
        this.paymentService = paymentService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== HOSPITAL PAYMENT MANAGEMENT ===");
            System.out.println("1. Record New Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. Search Payment by ID");
            System.out.println("4. View Payments by Invoice ID");
            System.out.println("5. Void / Delete Payment");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    recordPaymentUI();
                    break;
                case "2":
                    viewAllPaymentsUI();
                    break;
                case "3":
                    searchPaymentByIdUI();
                    break;
                case "4":
                    viewPaymentsByInvoiceUI();
                    break;
                case "5":
                    voidPaymentUI();
                    break;
                case "6":
                    exit = true;
                    System.out.println("Exiting Payment Management System...");
                    break;
                default:
                    System.out.println("Invalid option. Please select 1-6.");
            }
        }
    }

    private void recordPaymentUI() {
        try {
            System.out.println("\n--- Record New Payment ---");
            System.out.print("Enter Invoice ID: ");
            int invoiceId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Payment Amount ($): ");
            double amount = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter Payment Method (e.g., Cash, Credit Card, Insurance, Bank Transfer): ");
            String method = scanner.nextLine().trim();

            System.out.print("Enter Payment Date (YYYY-MM-DD) [Press Enter for Today]: ");
            String dateStr = scanner.nextLine().trim();
            LocalDate paymentDate = dateStr.isEmpty() ? LocalDate.now() : LocalDate.parse(dateStr, dateFormatter);

            Invoice invoice = new Invoice();
            invoice.setId(invoiceId);

            Payment created = paymentService.processPayment(invoice, amount, method, paymentDate);
            System.out.println("\n[SUCCESS] Payment recorded successfully!");
            printPaymentDetails(created);

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid numeric input entered.");
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to record payment: " + e.getMessage());
        }
    }

    private void viewAllPaymentsUI() {
        try {
            List<Payment> payments = paymentService.getAllPayments();
            if (payments.isEmpty()) {
                System.out.println("\nNo payment records found.");
                return;
            }
            System.out.println("\n--- All Recorded Payments ---");
            printPaymentTable(payments);
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to retrieve payments: " + e.getMessage());
        }
    }

    private void searchPaymentByIdUI() {
        try {
            System.out.print("\nEnter Payment ID to search: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Payment payment = paymentService.getPaymentById(id);
            if (payment == null) {
                System.out.println("[INFO] No payment record found with ID: " + id);
            } else {
                printPaymentDetails(payment);
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Error finding payment: " + e.getMessage());
        }
    }

    private void viewPaymentsByInvoiceUI() {
        try {
            System.out.print("\nEnter Invoice ID: ");
            int invoiceId = Integer.parseInt(scanner.nextLine().trim());
            List<Payment> payments = paymentService.getPaymentsByInvoiceId(invoiceId);
            if (payments.isEmpty()) {
                System.out.println("[INFO] No payments found for Invoice ID: " + invoiceId);
            } else {
                printPaymentTable(payments);
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Error finding payments for invoice: " + e.getMessage());
        }
    }

    private void voidPaymentUI() {
        try {
            System.out.print("\nEnter Payment ID to void/delete: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            boolean success = paymentService.voidPayment(id);
            if (success) {
                System.out.println("[SUCCESS] Payment ID " + id + " was voided successfully.");
            } else {
                System.out.println("[INFO] No payment found with ID " + id + " to void.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to void payment: " + e.getMessage());
        }
    }

    private void printPaymentTable(List<Payment> payments) {
        System.out.printf("%-10s %-12s %-12s %-15s %-15s\n", "Payment ID", "Invoice ID", "Amount ($)", "Method", "Date");
        System.out.println("------------------------------------------------------------------");
        for (Payment p : payments) {
            System.out.printf("%-10d %-12d %-12.2f %-15s %-15s\n",
                    p.getId(),
                    p.getInvoice() != null ? p.getInvoice().getId() : 0,
                    p.getAmount(),
                    p.getPaymentMethod(),
                    p.getPaymentDate() != null ? p.getPaymentDate().toString() : "N/A");
        }
    }

    private void printPaymentDetails(Payment p) {
        System.out.println("----------------------------------------");
        System.out.println(" Payment ID:     " + p.getId());
        System.out.println(" Invoice ID:     " + (p.getInvoice() != null ? p.getInvoice().getId() : "N/A"));
        System.out.println(" Amount:         $" + String.format("%.2f", p.getAmount()));
        System.out.println(" Payment Method: " + p.getPaymentMethod());
        System.out.println(" Payment Date:   " + p.getPaymentDate());
        System.out.println("----------------------------------------");
    }
}