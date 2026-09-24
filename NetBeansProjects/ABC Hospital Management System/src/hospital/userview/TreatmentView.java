package hospital.userview;

import hospital.models.Diagnosis;
import hospital.models.Doctor;
import hospital.models.Patient;
import hospital.models.Treatment;
import hospital.services.TreatmentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TreatmentView {

    private final TreatmentService treatmentService;
    private final Scanner scanner;

    public TreatmentView() {
        this.treatmentService = new TreatmentService();
        this.scanner = new Scanner(System.in);
    }

    public void createTreatmentPlan() {
        System.out.println("\n--- Create Treatment Plan ---");

        System.out.print("Enter Patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter Doctor ID: ");
        int doctorId = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter Diagnosis ID (Press Enter to skip): ");
        String diagInput = scanner.nextLine().trim();
        Diagnosis diagnosis = null;
        if (!diagInput.isEmpty()) {
            diagnosis = new Diagnosis();
            // Set Diagnosis ID
        }

        System.out.print("Enter Treatment Name: ");
        String treatmentName = scanner.nextLine().trim();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine().trim();

        System.out.print("Enter Status (e.g., Scheduled, In Progress, Completed): ");
        String status = scanner.nextLine().trim();
        if (status.isEmpty()) {
            status = "Scheduled";
        }

        Patient patient = new Patient();
        patient.setPatientId(patientId);

        Doctor doctor = new Doctor();
        doctor.setId(doctorId);

        Treatment treatment = new Treatment();
        treatment.setPatient(patient);
        treatment.setDoctor(doctor);
        treatment.setDiagnosis(diagnosis);
        treatment.setTreatmentDate(LocalDate.now());
        treatment.setTreatmentName(treatmentName);
        treatment.setDescription(description);
        treatment.setNotes(notes);
        treatment.setStatus(status);

        if (treatmentService.createTreatment(treatment)) {
            System.out.println("Treatment plan created successfully!");
        } else {
            System.out.println("Failed to create treatment plan.");
        }
    }

    public void viewPatientTreatments() {
        System.out.println("\n--- View Patient Treatments ---");
        System.out.print("Enter Patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine().trim());

        List<Treatment> treatments = treatmentService.getTreatmentsByPatientId(patientId);
        if (treatments.isEmpty()) {
            System.out.println("No treatment plans found for Patient ID: " + patientId);
            return;
        }

        for (Treatment t : treatments) {
            displayTreatment(t);
        }
    }

    public void updateTreatmentStatus() {
        System.out.println("\n--- Update Treatment Plan Status ---");
        System.out.print("Enter Treatment ID to Update: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Treatment treatment = treatmentService.getTreatmentById(id);
        if (treatment == null) {
            System.out.println("Treatment record not found.");
            return;
        }

        System.out.println("Current Status: " + treatment.getStatus());
        System.out.print("Enter New Status (Scheduled / In Progress / Completed / Cancelled): ");
        String newStatus = scanner.nextLine().trim();

        if (!newStatus.isEmpty()) {
            treatment.setStatus(newStatus);
        }

        System.out.print("Enter New Notes (Press Enter to keep existing): ");
        String notes = scanner.nextLine().trim();
        if (!notes.isEmpty()) {
            treatment.setNotes(notes);
        }

        if (treatmentService.updateTreatment(treatment)) {
            System.out.println("Treatment status updated successfully!");
        } else {
            System.out.println("Failed to update treatment status.");
        }
    }

    public void displayTreatment(Treatment treatment) {
        System.out.println("----------------------------------------");
        System.out.println("Treatment ID   : " + treatment.getId());
        System.out.println("Patient ID     : " + (treatment.getPatient() != null ? treatment.getPatient().getPatientId() : "N/A"));
        System.out.println("Doctor ID      : " + (treatment.getDoctor() != null ? treatment.getDoctor().getId() : "N/A"));
        System.out.println("Treatment Name : " + treatment.getTreatmentName());
        System.out.println("Date           : " + treatment.getTreatmentDate());
        System.out.println("Status         : " + treatment.getStatus());
        System.out.println("Description    : " + treatment.getDescription());
        System.out.println("Notes          : " + treatment.getNotes());
    }
}