package hospital.userview;

import hospital.models.Doctor;
import hospital.models.Patient;
import hospital.models.Prescription;
import hospital.services.PrescriptionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PrescriptionView {
    private final PrescriptionService prescriptionService;
    private final Scanner scanner;

    public PrescriptionView() {
        this.prescriptionService = new PrescriptionService();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== Prescription Management ===");
            System.out.println("1. Issue New Prescription");
            System.out.println("2. View All Prescriptions");
            System.out.println("3. View Prescription Details");
            System.out.println("4. Delete Prescription");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> createPrescription();
                case 2 -> viewAll();
                case 3 -> viewById();
                case 4 -> deletePrescription();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void createPrescription() {
        Prescription p = new Prescription();

        System.out.print("Enter Patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());
        Patient patient = new Patient();
        patient.setPatientId(patientId);
        p.setPatient(patient);

        System.out.print("Enter Doctor ID: ");
        int doctorId = Integer.parseInt(scanner.nextLine());
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        p.setDoctor(doctor);

        p.setPrescriptionDate(LocalDate.now());

        if (prescriptionService.addPrescription(p)) {
            System.out.println("Prescription issued successfully with ID: " + p.getId());
        } else {
            System.out.println("Failed to issue prescription.");
        }
    }

    private void viewAll() {
        List<Prescription> list = prescriptionService.getAllPrescriptions();
        if (list.isEmpty()) {
            System.out.println("No prescriptions found.");
            return;
        }
        System.out.printf("%-5s %-12s %-12s %-15s\n", "ID", "Patient ID", "Doctor ID", "Date");
        System.out.println("--------------------------------------------------");
        for (Prescription p : list) {
            System.out.printf("%-5d %-12d %-12d %-15s\n",
                    p.getId(),
                    p.getPatient().getPatientId(),
                    p.getDoctor().getId(),
                    p.getPrescriptionDate());
        }
    }

    private void viewById() {
        System.out.print("Enter Prescription ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Prescription p = prescriptionService.getPrescription(id);
        if (p != null) {
            System.out.println("Prescription ID: " + p.getId());
            System.out.println("Patient ID: " + p.getPatient().getPatientId());
            System.out.println("Doctor ID: " + p.getDoctor().getId());
            System.out.println("Date: " + p.getPrescriptionDate());
        } else {
            System.out.println("Prescription not found.");
        }
    }

    private void deletePrescription() {
        System.out.print("Enter Prescription ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (prescriptionService.deletePrescription(id)) {
            System.out.println("Prescription deleted successfully.");
        } else {
            System.out.println("Failed to delete prescription.");
        }
    }
}