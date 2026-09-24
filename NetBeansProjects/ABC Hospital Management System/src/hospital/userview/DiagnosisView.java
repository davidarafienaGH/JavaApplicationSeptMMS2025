package hospital.userview;

import hospital.models.Diagnosis;
import hospital.models.Doctor;
import hospital.models.Patient;
import hospital.services.DiagnosisService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DiagnosisView {

    private final DiagnosisService diagnosisService = new DiagnosisService();
    private final Scanner scanner = new Scanner(System.in);

    public void createDiagnosis() {
        System.out.println("\n--- Create New Diagnosis ---");
        
        System.out.print("Enter Patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Doctor ID: ");
        int doctorId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Condition: ");
        String condition = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine();

        Patient patient = new Patient();
        patient.setPatientId(patientId);

        Doctor doctor = new Doctor();
        // Assign doctor parameters...

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setPatient(patient);
        diagnosis.setDoctor(doctor);
        diagnosis.setDiagnosisDate(LocalDate.now());
        diagnosis.setCondition(condition);
        diagnosis.setDescription(description);
        diagnosis.setNotes(notes);

        if (diagnosisService.createDiagnosis(diagnosis)) {
            System.out.println("Diagnosis created successfully!");
        } else {
            System.out.println("Failed to create diagnosis.");
        }
    }

    public void viewAllDiagnoses() {
        System.out.println("\n--- All Diagnoses ---");
        List<Diagnosis> diagnoses = diagnosisService.getAllDiagnoses();
        if (diagnoses.isEmpty()) {
            System.out.println("No diagnosis records found.");
            return;
        }

        for (Diagnosis d : diagnoses) {
            displayDiagnosis(d);
        }
    }

    public void findDiagnosisById() {
        System.out.println("\n--- Find Diagnosis ---");
        System.out.print("Enter Diagnosis ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Diagnosis diagnosis = diagnosisService.getDiagnosisById(id);
        if (diagnosis != null) {
            displayDiagnosis(diagnosis);
        } else {
            System.out.println("Diagnosis not found with ID: " + id);
        }
    }

    public void updateDiagnosis() {
        System.out.println("\n--- Update Diagnosis ---");
        System.out.print("Enter Diagnosis ID to Update: ");
        int id = Integer.parseInt(scanner.nextLine());

        Diagnosis diagnosis = diagnosisService.getDiagnosisById(id);
        if (diagnosis == null) {
            System.out.println("Diagnosis record not found.");
            return;
        }

        System.out.print("Enter New Condition (" + diagnosis.getCondition() + "): ");
        String condition = scanner.nextLine();
        if (!condition.trim().isEmpty()) {
            diagnosis.setCondition(condition);
        }

        System.out.print("Enter New Description (" + diagnosis.getDescription() + "): ");
        String description = scanner.nextLine();
        if (!description.trim().isEmpty()) {
            diagnosis.setDescription(description);
        }

        System.out.print("Enter New Notes (" + diagnosis.getNotes() + "): ");
        String notes = scanner.nextLine();
        if (!notes.trim().isEmpty()) {
            diagnosis.setNotes(notes);
        }

        if (diagnosisService.updateDiagnosis(diagnosis)) {
            System.out.println("Diagnosis updated successfully!");
        } else {
            System.out.println("Failed to update diagnosis.");
        }
    }

    public void deleteDiagnosis() {
        System.out.println("\n--- Delete Diagnosis ---");
        System.out.print("Enter Diagnosis ID to Delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (diagnosisService.deleteDiagnosis(id)) {
            System.out.println("Diagnosis deleted successfully!");
        } else {
            System.out.println("Failed to delete diagnosis or record not found.");
        }
    }

    public void displayDiagnosis(Diagnosis d) {
        System.out.println("----------------------------------------");
        System.out.println("Diagnosis ID: " + d.getId());
        System.out.println("Patient ID  : " + (d.getPatient() != null ? d.getPatient().getPatientId() : "N/A"));
        System.out.println("Doctor ID   : " + (d.getDoctor() != null ? d.getDoctor().getId() : "N/A"));
        System.out.println("Date        : " + d.getDiagnosisDate());
        System.out.println("Condition   : " + d.getCondition());
        System.out.println("Description : " + d.getDescription());
        System.out.println("Notes       : " + d.getNotes());
        System.out.println("----------------------------------------");
    }
}