package hospital.services;

import hospital.dao.DiagnosisDAO;
import hospital.models.Diagnosis;

import java.util.List;

public class DiagnosisService {

    private final DiagnosisDAO diagnosisDAO;

    public DiagnosisService() {
        this.diagnosisDAO = new DiagnosisDAO();
    }

    public boolean createDiagnosis(Diagnosis diagnosis) {
        if (diagnosis == null || diagnosis.getPatient() == null || diagnosis.getDoctor() == null) {
            System.out.println("Invalid diagnosis details provided.");
            return false;
        }
        return diagnosisDAO.createDiagnosis(diagnosis);
    }

    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisDAO.getAllDiagnoses();
    }

    public Diagnosis getDiagnosisById(int id) {
        if (id <= 0) {
            return null;
        }
        return diagnosisDAO.getDiagnosisById(id);
    }

    public boolean updateDiagnosis(Diagnosis diagnosis) {
        if (diagnosis == null || diagnosis.getId() <= 0) {
            System.out.println("Invalid diagnosis record for update.");
            return false;
        }
        return diagnosisDAO.updateDiagnosis(diagnosis);
    }

    public boolean deleteDiagnosis(int id) {
        if (id <= 0) {
            return false;
        }
        return diagnosisDAO.deleteDiagnosis(id);
    }
}