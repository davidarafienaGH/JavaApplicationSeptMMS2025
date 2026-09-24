package hospital.userview;

import hospital.models.Diagnosis;
import hospital.models.MedicalRecord;
import hospital.models.Treatment;
import hospital.services.MedicalRecordService;

import java.util.List;
import java.util.Scanner;

public class MedicalRecordView {

    private final MedicalRecordService medicalRecordService;
    private final Scanner scanner;

    public MedicalRecordView() {
        this.medicalRecordService = new MedicalRecordService();
        this.scanner = new Scanner(System.in);
    }

    public void createMedicalRecordFlow() {
        System.out.println("\n--- Create Medical Record ---");
        System.out.print("Enter Patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine().trim());

        if (medicalRecordService.createMedicalRecord(patientId)) {
            System.out.println("Medical record created successfully for Patient ID: " + patientId);
        } else {
            System.out.println("Failed to create medical record.");
        }
    }

    public void displayFullPatientRecord() {
        System.out.println("\n--- Full Patient Chart / Medical Record ---");
        System.out.print("Enter Patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine().trim());

        MedicalRecord record = medicalRecordService.getFullMedicalRecord(patientId);

        if (record == null) {
            System.out.println("No medical record chart found for Patient ID: " + patientId);
            return;
        }

        System.out.println("==============================================================");
        System.out.println("                   PATIENT MEDICAL RECORD CHART               ");
        System.out.println("==============================================================");
        System.out.println("Patient ID    : " + record.getPatient().getPatientId());
        System.out.println("Record Created: " + record.getCreatedDate());
        System.out.println("--------------------------------------------------------------");

        System.out.println("\n[DIAGNOSES HISTORY]");
        if (record.getDiagnoses().isEmpty()) {
            System.out.println("No diagnostic records recorded.");
        } else {
            for (Diagnosis d : record.getDiagnoses()) {
                System.out.println("  - Date       : " + d.getDiagnosisDate());
                System.out.println("    Condition  : " + d.getCondition());
                System.out.println("    Description: " + d.getDescription());
                System.out.println("    Notes      : " + d.getNotes());
                System.out.println("  --------------------------------------------------------");
            }
        }

        System.out.println("\n[TREATMENT PLANS]");
        if (record.getTreatments().isEmpty()) {
            System.out.println("No treatment plans recorded.");
        } else {
            for (Treatment t : record.getTreatments()) {
                System.out.println("  - Date       : " + t.getTreatmentDate());
                System.out.println("    Treatment  : " + t.getTreatmentName());
                System.out.println("    Status     : " + t.getStatus());
                System.out.println("    Description: " + t.getDescription());
                System.out.println("    Notes      : " + t.getNotes());
                System.out.println("  --------------------------------------------------------");
            }
        }
        System.out.println("==============================================================");
    }

    public void displayAllMedicalRecords() {
        System.out.println("\n--- All Registered Patient Charts ---");
        List<MedicalRecord> records = medicalRecordService.getAllMedicalRecords();

        if (records.isEmpty()) {
            System.out.println("No medical records registered in system.");
            return;
        }

        for (MedicalRecord r : records) {
            System.out.printf("Record ID: %-5d | Patient ID: %-5d | Created Date: %s%n",
                    r.getId(),
                    (r.getPatient() != null ? r.getPatient().getPatientId() : 0),
                    r.getCreatedDate());
        }
    }
}