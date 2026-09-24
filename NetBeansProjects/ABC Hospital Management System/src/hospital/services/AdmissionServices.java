package hospital.services;

import hospital.dao.AdmissionDAO;
import hospital.dao.BedDAO;
import hospital.models.Admission;
import hospital.models.Bed;

import java.time.LocalDate;
import java.util.List;

public class AdmissionServices {

    private final AdmissionDAO admissionDAO;
    private final BedDAO bedDAO;

    public AdmissionServices() {
        this.admissionDAO = new AdmissionDAO();
        this.bedDAO = new BedDAO();
    }

    public boolean admitPatient(Admission admission) {
        if (admission.getPatient() == null) {
            System.out.println("Patient is required.");
            return false;
        }
        if (admission.getBed() == null) {
            System.out.println("Bed is required.");
            return false;
        }

        if (admission.getAdmissionDate() == null) {
            admission.setAdmissionDate(LocalDate.now());
        }

        if (admission.getStatus() == null || admission.getStatus().isBlank()) {
            admission.setStatus("Admitted");
        }

        return admissionDAO.addAdmission(admission);
    }

    public Admission getAdmissionById(int admissionId) {
        return admissionDAO.getAdmissionById(admissionId);
    }

    public List<Admission> getAllAdmissions() {
        return admissionDAO.getAllAdmissions();
    }

    public List<Admission> getAdmissionsByPatient(int patientId) {
        return admissionDAO.getAdmissionsByPatient(patientId);
    }

    public boolean dischargePatient(int admissionId, LocalDate dischargeDate) {
        Admission admission = admissionDAO.getAdmissionById(admissionId);
        if (admission == null) {
            System.out.println("Admission not found.");
            return false;
        }

        if ("Discharged".equalsIgnoreCase(admission.getStatus())) {
            System.out.println("Patient is already discharged.");
            return false;
        }

        if (dischargeDate == null) {
            dischargeDate = LocalDate.now();
        }

        if (admission.getAdmissionDate() != null && dischargeDate.isBefore(admission.getAdmissionDate())) {
            System.out.println("Discharge date cannot be before admission date.");
            return false;
        }

        return admissionDAO.dischargePatient(admissionId, dischargeDate);
    }
    
    public boolean updateAdmission(Admission admission) {
    return admissionDAO.updateAdmission(admission);
}

    public boolean deleteAdmission(int admissionId) {
        return admissionDAO.deleteAdmission(admissionId);
    }

    public Bed getBedById(int bedId) {
        return bedDAO.getBedById(bedId);
    }

    public List<Bed> getAvailableBeds() {
        return bedDAO.getAvailableBeds();
    }
}