package hospital.services;

import hospital.dao.PrescriptionDAO;
import hospital.models.Prescription;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionService {
    private final PrescriptionDAO prescriptionDAO;

    public PrescriptionService() {
        this.prescriptionDAO = new PrescriptionDAO();
    }

    public boolean addPrescription(Prescription prescription) {
        if (prescription.getPatient() == null || prescription.getDoctor() == null) {
            System.out.println("Validation Error: Patient and Doctor must be specified.");
            return false;
        }
        if (prescription.getPrescriptionDate() == null) {
            prescription.setPrescriptionDate(LocalDate.now());
        }
        return prescriptionDAO.createPrescription(prescription);
    }

    public Prescription getPrescription(int id) {
        return prescriptionDAO.getPrescriptionById(id);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAO.getAllPrescriptions();
    }

    public boolean deletePrescription(int id) {
        return prescriptionDAO.deletePrescription(id);
    }
}