package hospital.userview;

import hospital.models.Medication;
import hospital.services.MedicationService;

import java.util.List;
import java.util.Scanner;

public class MedicationView {
    private final MedicationService medicationService;
    private final Scanner scanner;

    public MedicationView() {
        this.medicationService = new MedicationService();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== Medication Management ===");
            System.out.println("1. Add Medication");
            System.out.println("2. View All Medications");
            System.out.println("3. View Medication Details");
            System.out.println("4. Update Medication");
            System.out.println("5. Delete Medication");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addMedication();
                case 2 -> viewAll();
                case 3 -> viewById();
                case 4 -> updateMedication();
                case 5 -> deleteMedication();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addMedication() {
        Medication med = new Medication();
        System.out.print("Name: ");
        med.setName(scanner.nextLine());
        System.out.print("Description: ");
        med.setDescription(scanner.nextLine());
        System.out.print("Dosage Form (e.g., Tablet, Capsule): ");
        med.setDosageForm(scanner.nextLine());
        System.out.print("Price: ");
        med.setPrice(Double.parseDouble(scanner.nextLine()));
        System.out.print("Quantity in Stock: ");
        med.setQuantityInStock(Integer.parseInt(scanner.nextLine()));

        if (medicationService.addMedication(med)) {
            System.out.println("Medication added successfully!");
        } else {
            System.out.println("Failed to add medication.");
        }
    }

    private void viewAll() {
        List<Medication> list = medicationService.getAllMedications();
        if (list.isEmpty()) {
            System.out.println("No medications found.");
            return;
        }
        System.out.printf("%-5s %-20s %-15s %-10s %-10s\n", "ID", "Name", "Form", "Price", "Stock");
        System.out.println("---------------------------------------------------------------");
        for (Medication m : list) {
            System.out.printf("%-5d %-20s %-15s $%-9.2f %-10d\n",
                    m.getId(), m.getName(), m.getDosageForm(), m.getPrice(), m.getQuantityInStock());
        }
    }

    private void viewById() {
        System.out.print("Enter Medication ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Medication m = medicationService.getMedication(id);
        if (m != null) {
            System.out.println("ID: " + m.getId());
            System.out.println("Name: " + m.getName());
            System.out.println("Description: " + m.getDescription());
            System.out.println("Dosage Form: " + m.getDosageForm());
            System.out.println("Price: $" + m.getPrice());
            System.out.println("Stock: " + m.getQuantityInStock());
        } else {
            System.out.println("Medication not found.");
        }
    }

    private void updateMedication() {
        System.out.print("Enter Medication ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Medication m = medicationService.getMedication(id);
        if (m == null) {
            System.out.println("Medication not found.");
            return;
        }

        System.out.print("New Name (" + m.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isBlank()) m.setName(name);

        System.out.print("New Description (" + m.getDescription() + "): ");
        String desc = scanner.nextLine();
        if (!desc.isBlank()) m.setDescription(desc);

        System.out.print("New Price (" + m.getPrice() + "): ");
        String priceStr = scanner.nextLine();
        if (!priceStr.isBlank()) m.setPrice(Double.parseDouble(priceStr));

        System.out.print("New Stock (" + m.getQuantityInStock() + "): ");
        String stockStr = scanner.nextLine();
        if (!stockStr.isBlank()) m.setQuantityInStock(Integer.parseInt(stockStr));

        if (medicationService.updateMedication(m)) {
            System.out.println("Medication updated successfully.");
        } else {
            System.out.println("Failed to update medication.");
        }
    }

    private void deleteMedication() {
        System.out.print("Enter Medication ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (medicationService.deleteMedication(id)) {
            System.out.println("Medication deleted successfully.");
        } else {
            System.out.println("Failed to delete medication.");
        }
    }
}