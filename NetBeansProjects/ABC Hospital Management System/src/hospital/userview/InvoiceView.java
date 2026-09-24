package hospital.userview;

import hospital.models.Invoice;
import hospital.models.Patient;
import hospital.services.InvoiceService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class InvoiceView {

    private final InvoiceService invoiceService;
    private final Scanner scanner;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public InvoiceView(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== INVOICE MANAGEMENT ===");
            System.out.println("1. Create New Invoice");
            System.out.println("2. View All Invoices");
            System.out.println("3. Search Invoice by ID");
            System.out.println("4. View Invoices by Patient ID");
            System.out.println("5. Update Invoice Status");
            System.out.println("6. Delete Invoice");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": createInvoiceUI(); break;
                case "2": viewAllInvoicesUI(); break;
                case "3": searchInvoiceByIdUI(); break;
                case "4": viewInvoicesByPatientUI(); break;
                case "5": updateInvoiceStatusUI(); break;
                case "6": deleteInvoiceUI(); break;
                case "7": exit = true; break;
                default: System.out.println("Invalid choice. Please select 1-7.");
            }
        }
    }

    private void createInvoiceUI() {
        try {
            System.out.print("Enter Patient ID: ");
            int patientId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Date (YYYY-MM-DD) [Leave blank for Today]: ");
            String dateStr = scanner.nextLine().trim();
            LocalDate date = dateStr.isEmpty() ? LocalDate.now() : LocalDate.parse(dateStr, dateFormatter);

            System.out.print("Enter Status (e.g. Pending, Paid, Cancelled) [Default: Pending]: ");
            String status = scanner.nextLine().trim();

            Patient patient = new Patient();
            patient.setPatientId(patientId);

            Invoice created = invoiceService.createInvoice(patient, date, status);
            System.out.println("\n[SUCCESS] Invoice Created Successfully with ID: " + created.getId());
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to create invoice: " + e.getMessage());
        }
    }

    private void viewAllInvoicesUI() {
        try {
            List<Invoice> invoices = invoiceService.getAllInvoices();
            if (invoices.isEmpty()) {
                System.out.println("No invoices found.");
                return;
            }
            printInvoiceTable(invoices);
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to fetch invoices: " + e.getMessage());
        }
    }

    private void searchInvoiceByIdUI() {
        try {
            System.out.print("Enter Invoice ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Invoice invoice = invoiceService.getInvoiceById(id);
            if (invoice != null) {
                printInvoiceTable(List.of(invoice));
            } else {
                System.out.println("Invoice not found.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Error searching invoice: " + e.getMessage());
        }
    }

    private void viewInvoicesByPatientUI() {
        try {
            System.out.print("Enter Patient ID: ");
            int patientId = Integer.parseInt(scanner.nextLine().trim());
            List<Invoice> invoices = invoiceService.getInvoicesByPatientId(patientId);
            if (invoices.isEmpty()) {
                System.out.println("No invoices found for Patient ID: " + patientId);
            } else {
                printInvoiceTable(invoices);
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Error fetching invoices: " + e.getMessage());
        }
    }

    private void updateInvoiceStatusUI() {
        try {
            System.out.print("Enter Invoice ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter New Status: ");
            String status = scanner.nextLine().trim();

            if (invoiceService.updateStatus(id, status)) {
                System.out.println("[SUCCESS] Invoice status updated.");
            } else {
                System.out.println("[ERROR] Invoice ID not found.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Update failed: " + e.getMessage());
        }
    }

    private void deleteInvoiceUI() {
        try {
            System.out.print("Enter Invoice ID to Delete: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            if (invoiceService.deleteInvoice(id)) {
                System.out.println("[SUCCESS] Invoice deleted.");
            } else {
                System.out.println("[ERROR] Invoice ID not found.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Delete failed: " + e.getMessage());
        }
    }

    private void printInvoiceTable(List<Invoice> invoices) {
        System.out.printf("\n%-12s %-12s %-15s %-15s %-12s\n", "Invoice ID", "Patient ID", "Date", "Total Amount", "Status");
        System.out.println("------------------------------------------------------------------");
        for (Invoice i : invoices) {
            System.out.printf("%-12d %-12d %-15s $%-14.2f %-12s\n",
                    i.getId(),
                    i.getPatient() != null ? i.getPatient().getPatientId() : 0,
                    i.getInvoiceDate() != null ? i.getInvoiceDate().toString() : "N/A",
                    i.getTotalAmount(),
                    i.getStatus());
        }
    }
}