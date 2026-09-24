package hospital.services;

import hospital.dao.TreatmentDAO;
import hospital.models.Treatment;
import java.util.List;

public class TreatmentService {

    private final TreatmentDAO treatmentDAO;

    public TreatmentService() {
        this.treatmentDAO = new TreatmentDAO();
    }

    public boolean createTreatment(Treatment treatment) {
        if (treatment == null || treatment.getPatient() == null || treatment.getDoctor() == null) {
            System.out.println("Invalid treatment details provided.");
            return false;
        }
        if (treatment.getTreatmentName() == null || treatment.getTreatmentName().trim().isEmpty()) {
            System.out.println("Treatment name cannot be empty.");
            return false;
        }
        return treatmentDAO.createTreatment(treatment);
    }

    public List<Treatment> getAllTreatments() {
        return treatmentDAO.getAllTreatments();
    }

    public Treatment getTreatmentById(int id) {
        return treatmentDAO.getTreatmentById(id);
    }

    public List<Treatment> getTreatmentsByPatientId(int patientId) {
        return treatmentDAO.getTreatmentsByPatientId(patientId);
    }

    public boolean updateTreatment(Treatment treatment) {
        if (treatment == null || treatment.getId() <= 0) {
            System.out.println("Invalid treatment record for update.");
            return false;
        }
        return treatmentDAO.updateTreatment(treatment);
    }

    public boolean deleteTreatment(int id) {
        return treatmentDAO.deleteTreatment(id);
    }
}