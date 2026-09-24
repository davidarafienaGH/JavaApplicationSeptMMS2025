package hospital.userview;

import hospital.models.LaboratoryTechnician;
import hospital.models.LaboratoryTest;
import hospital.models.Patient;
import hospital.services.LaboratoryTestService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LaboratoryTestView {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final LaboratoryTestService labTestService;
    private final Scanner scanner;

    public LaboratoryTestView() {
        this.labTestService = new LaboratoryTestService();
        this.scanner = new Scanner(System.in);
    }

    public void displayTests(List<LaboratoryTest> tests) {
        System.out.println("======================================================================");
        System.out.println("                       LABORATORY TESTS                               ");
        System.out.println("======================================================================");
        if (tests == null || tests.isEmpty()) {
            System.out.println("No laboratory tests found.");
            return;
        }

        for (LaboratoryTest test : tests) {
            displaySingleTest(test);
        }
    }

    public void displaySingleTest(LaboratoryTest test) {
        if (test == null) {
            System.out.println("Laboratory test not found.");
            return;
        }
        System.out.println("Test ID        : " + test.getId());
        System.out.println("Patient        : " + getPatientName(test));
        System.out.println("Technician     : " + getTechnicianName(test));
        System.out.println("Test Name      : " + test.getTestName());
        System.out.println("Test Date      : " + (test.getTestDate() != null ? test.getTestDate().format(formatter) : "N/A"));
        System.out.println("Result         : " + (test.getResult() != null ? test.getResult() : "Not available"));
        System.out.println("Reference Range: " + (test.getReferenceRange() != null ? test.getReferenceRange() : "N/A"));
        System.out.println("Status         : " + (test.getStatus() != null ? test.getStatus() : "N/A"));
        System.out.println("----------------------------------------------------------------------");
    }

    // Interactive Workflows

    public void createLaboratoryTestFlow() {
        System.out.println("\n--- Create Laboratory Test ---");
        System.out.print("Enter Patient ID: ");
        int patientId = readInt();

        System.out.print("Enter Technician ID (0 to skip): ");
        int techId = readInt();

        System.out.print("Enter Test Name: ");
        String testName = scanner.nextLine().trim();

        System.out.print("Enter Reference Range (e.g. 70-99 mg/dL): ");
        String referenceRange = scanner.nextLine().trim();

        LaboratoryTest test = new LaboratoryTest();
        Patient patient = new Patient();
        patient.setPatientId(patientId);
        test.setPatient(patient);

        if (techId > 0) {
            LaboratoryTechnician tech = new LaboratoryTechnician();
            tech.setStaffId(techId);
            test.setTechnician(tech);
        }

        test.setTestName(testName);
        test.setReferenceRange(referenceRange);
        test.setTestDate(LocalDateTime.now());
        test.setStatus("Pending");

        int generatedId = labTestService.createTest(test);
        if (generatedId > 0) {
            System.out.println("Laboratory test created successfully! (ID: " + generatedId + ")");
        } else {
            System.out.println("Failed to create laboratory test.");
        }
    }

    public void viewAllLaboratoryTestsFlow() {
        displayTests(labTestService.getAllTests());
    }

    public void findLaboratoryTestFlow() {
        System.out.print("\nEnter Test ID: ");
        int id = readInt();
        LaboratoryTest test = labTestService.findTest(id);
        displaySingleTest(test);
    }

    public void updateLaboratoryTestFlow() {
        System.out.print("\nEnter Test ID to update: ");
        int id = readInt();
        LaboratoryTest test = labTestService.findTest(id);

        if (test == null) {
            System.out.println("Laboratory test not found.");
            return;
        }

        System.out.print("Enter New Test Name (Leave blank to keep '" + test.getTestName() + "'): ");
        String testName = scanner.nextLine().trim();
        if (!testName.isEmpty()) {
            test.setTestName(testName);
        }

        System.out.print("Enter New Reference Range (Leave blank to keep current): ");
        String refRange = scanner.nextLine().trim();
        if (!refRange.isEmpty()) {
            test.setReferenceRange(refRange);
        }

        System.out.print("Enter New Status (Pending / In Progress / Completed / Cancelled): ");
        String status = scanner.nextLine().trim();
        if (!status.isEmpty()) {
            test.setStatus(status);
        }

        if (labTestService.updateTest(test)) {
            System.out.println("Laboratory test updated successfully!");
        } else {
            System.out.println("Failed to update laboratory test.");
        }
    }

    public void deleteLaboratoryTestFlow() {
        System.out.print("\nEnter Test ID to delete: ");
        int id = readInt();

        if (labTestService.deleteTest(id)) {
            System.out.println("Laboratory test deleted successfully!");
        } else {
            System.out.println("Failed to delete laboratory test.");
        }
    }

    public void viewPatientTestsFlow() {
        System.out.print("\nEnter Patient ID: ");
        int patientId = readInt();
        displayTests(labTestService.getTestsByPatientId(patientId));
    }

    public void viewPendingTestsFlow() {
        System.out.println("\n--- Pending Laboratory Tests ---");
        displayTests(labTestService.getPendingTests());
    }

    public void viewCompletedTestsFlow() {
        System.out.println("\n--- Completed Laboratory Tests ---");
        displayTests(labTestService.getCompletedTests());
    }

    public void recordTestResultFlow() {
        System.out.println("\n--- Record Laboratory Test Result ---");
        System.out.print("Enter Test ID: ");
        int testId = readInt();

        System.out.print("Enter Result Description/Value: ");
        String result = scanner.nextLine().trim();

        System.out.print("Enter Status (Press Enter for 'Completed'): ");
        String status = scanner.nextLine().trim();
        if (status.isEmpty()) {
            status = "Completed";
        }

        if (labTestService.updateResult(testId, result, status)) {
            System.out.println("Test result recorded successfully!");
        } else {
            System.out.println("Failed to record test result.");
        }
    }

    private String getPatientName(LaboratoryTest test) {
        if (test.getPatient() != null) {
            String first = test.getPatient().getFirstName();
            String last = test.getPatient().getLastName();
            if (first != null || last != null) {
                return (first != null ? first : "") + " " + (last != null ? last : "");
            }
            return "ID: " + test.getPatient().getPatientId();
        }
        return "Not assigned";
    }

    private String getTechnicianName(LaboratoryTest test) {
        if (test.getTechnician() != null) {
            String first = test.getTechnician().getFirstName();
            String last = test.getTechnician().getLastName();
            if (first != null || last != null) {
                return (first != null ? first : "") + " " + (last != null ? last : "");
            }
            return "ID: " + test.getTechnician().getStaffId();
        }
        return "Not assigned";
    }

    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}