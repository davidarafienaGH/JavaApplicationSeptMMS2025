package hospital.userview;

import hospital.models.Invoice;
import hospital.models.InvoiceItem;
import hospital.services.InvoiceItemService;

import java.util.List;
import java.util.Scanner;

public class InvoiceItemView {

    private final InvoiceItemService itemService;
    private final Scanner scanner;

    public InvoiceItemView(InvoiceItemService itemService) {
        this.itemService = itemService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== INVOICE ITEM LINE MANAGEMENT ===");
            System.out.println("1. Add Item Line to Invoice");
            System.out.println("2. View All Items for an Invoice");
            System.out.println("3. Remove Item Line");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": addItemUI(); break;
                case "2": viewItemsByInvoiceUI(); break;
                case "3": removeItemUI(); break;
                case "4": exit = true; break;
                default: System.out.println("Invalid choice. Please select 1-4.");
            }
        }
    }

    private void addItemUI() {
        try {
            System.out.print("Enter Invoice ID: ");
            int invoiceId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Unit Price ($): ");
            double unitPrice = Double.parseDouble(scanner.nextLine().trim());

            Invoice invoice = new Invoice();
            invoice.setId(invoiceId);

            InvoiceItem created = itemService.addInvoiceItem(invoice, description, quantity, unitPrice);
            System.out.println("\n[SUCCESS] Line item added successfully (ID: " + created.getId() + ")");
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to add line item: " + e.getMessage());
        }
    }

    private void viewItemsByInvoiceUI() {
        try {
            System.out.print("Enter Invoice ID: ");
            int invoiceId = Integer.parseInt(scanner.nextLine().trim());

            List<InvoiceItem> items = itemService.getItemsByInvoiceId(invoiceId);
            if (items.isEmpty()) {
                System.out.println("No line items found for Invoice ID: " + invoiceId);
            } else {
                printItemTable(items);
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to fetch items: " + e.getMessage());
        }
    }

    private void removeItemUI() {
        try {
            System.out.print("Enter Line Item ID to Remove: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            if (itemService.removeInvoiceItem(id)) {
                System.out.println("[SUCCESS] Item line removed and total recalculated.");
            } else {
                System.out.println("[ERROR] Line item ID not found.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to remove line item: " + e.getMessage());
        }
    }

    private void printItemTable(List<InvoiceItem> items) {
        System.out.printf("\n%-10s %-12s %-30s %-10s %-12s %-12s\n", "Item ID", "Invoice ID", "Description", "Qty", "Unit Price", "Total Amount");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (InvoiceItem item : items) {
            System.out.printf("%-10d %-12d %-30s %-10d $%-11.2f $%-11.2f\n",
                    item.getId(),
                    item.getInvoice() != null ? item.getInvoice().getId() : 0,
                    item.getDescription(),
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getAmount());
        }
    }
}