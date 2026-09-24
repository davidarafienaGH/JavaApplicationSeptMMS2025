package hospital.services;

import hospital.dao.MedicationDAO;
import hospital.models.Medication;

import java.util.List;

public class MedicationService {
    private final MedicationDAO medicationDAO;

    public MedicationService() {
        this.medicationDAO = new MedicationDAO();
    }

    public boolean addMedication(Medication medication) {
        if (medication.getPrice() < 0 || medication.getQuantityInStock() < 0) {
            System.out.println("Validation Error: Price and stock quantity cannot be negative.");
            return false;
        }
        return medicationDAO.createMedication(medication);
    }

    public Medication getMedication(int id) {
        return medicationDAO.getMedicationById(id);
    }

    public List<Medication> getAllMedications() {
        return medicationDAO.getAllMedications();
    }

    public boolean updateMedication(Medication medication) {
        return medicationDAO.updateMedication(medication);
    }

    public boolean deleteMedication(int id) {
        return medicationDAO.deleteMedication(id);
    }
}