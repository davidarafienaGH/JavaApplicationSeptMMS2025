package hospital.services;

import hospital.dao.LaboratoryTestDAO;
import hospital.models.LaboratoryTest;

import java.util.ArrayList;
import java.util.List;

public class LaboratoryTestService {

    private final LaboratoryTestDAO laboratoryTestDAO;

    public LaboratoryTestService() {
        this.laboratoryTestDAO = new LaboratoryTestDAO();
    }

    public int createTest(LaboratoryTest test) {
        if (test == null) {
            System.out.println("Laboratory test cannot be null.");
            return -1;
        }
        if (test.getPatient() == null) {
            System.out.println("Patient is required.");
            return -1;
        }
        if (test.getTestName() == null || test.getTestName().trim().isEmpty()) {
            System.out.println("Test name is required.");
            return -1;
        }
        if (test.getStatus() == null || test.getStatus().trim().isEmpty()) {
            test.setStatus("Pending");
        }
        return laboratoryTestDAO.create(test);
    }

    public List<LaboratoryTest> getAllTests() {
        return laboratoryTestDAO.getAll();
    }

    public LaboratoryTest findTest(int id) {
        if (id <= 0) {
            System.out.println("Invalid laboratory test ID.");
            return null;
        }
        return laboratoryTestDAO.findById(id);
    }

    public boolean updateTest(LaboratoryTest test) {
        if (test == null || test.getId() <= 0) {
            System.out.println("Invalid laboratory test for update.");
            return false;
        }
        return laboratoryTestDAO.update(test);
    }

    public boolean updateResult(int testId, String result, String status) {
        if (testId <= 0) {
            System.out.println("Invalid laboratory test ID.");
            return false;
        }
        if (result == null || result.trim().isEmpty()) {
            System.out.println("Laboratory result is required.");
            return false;
        }
        if (status == null || status.trim().isEmpty()) {
            status = "Completed";
        }
        return laboratoryTestDAO.updateResult(testId, result, status);
    }

    public boolean deleteTest(int id) {
        if (id <= 0) {
            System.out.println("Invalid laboratory test ID.");
            return false;
        }
        return laboratoryTestDAO.delete(id);
    }

    // Filter Helpers
    public List<LaboratoryTest> getTestsByPatientId(int patientId) {
        List<LaboratoryTest> result = new ArrayList<>();
        for (LaboratoryTest t : getAllTests()) {
            if (t.getPatient() != null && t.getPatient().getPatientId() == patientId) {
                result.add(t);
            }
        }
        return result;
    }

    public List<LaboratoryTest> getPendingTests() {
        List<LaboratoryTest> result = new ArrayList<>();
        for (LaboratoryTest t : getAllTests()) {
            if ("Pending".equalsIgnoreCase(t.getStatus())) {
                result.add(t);
            }
        }
        return result;
    }

    public List<LaboratoryTest> getCompletedTests() {
        List<LaboratoryTest> result = new ArrayList<>();
        for (LaboratoryTest t : getAllTests()) {
            if ("Completed".equalsIgnoreCase(t.getStatus())) {
                result.add(t);
            }
        }
        return result;
    }
}