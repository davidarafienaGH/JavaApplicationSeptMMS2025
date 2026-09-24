package hospital.services;

import hospital.dao.MedicalRecordDAO;
import hospital.models.Diagnosis;
import hospital.models.MedicalRecord;
import hospital.models.Treatment;

import java.time.LocalDate;
import java.util.List;

public class MedicalRecordService {

    private final MedicalRecordDAO medicalRecordDAO;
    private final DiagnosisService diagnosisService;
    private final TreatmentService treatmentService;

    public MedicalRecordService() {
        this.medicalRecordDAO = new MedicalRecordDAO();
        this.diagnosisService = new DiagnosisService();
        this.treatmentService = new TreatmentService();
    }

    public boolean createMedicalRecord(int patientId) {
        if (patientId <= 0) {
            System.out.println("Invalid Patient ID.");
            return false;
        }

        MedicalRecord existing = medicalRecordDAO.getMedicalRecordByPatientId(patientId);
        if (existing != null) {
            System.out.println("Medical record already exists for Patient ID: " + patientId);
            return false;
        }

        MedicalRecord record = new MedicalRecord();
        hospital.models.Patient patient = new hospital.models.Patient();
        patient.setPatientId(patientId);

        record.setPatient(patient);
        record.setCreatedDate(LocalDate.now());

        return medicalRecordDAO.createMedicalRecord(record);
    }

    public MedicalRecord getFullMedicalRecord(int patientId) {
        MedicalRecord record = medicalRecordDAO.getMedicalRecordByPatientId(patientId);
        if (record == null) {
            return null;
        }

        // Hydrate record collections from respective service layers
        List<Diagnosis> diagnoses = diagnosisService.getAllDiagnoses();
        for (Diagnosis d : diagnoses) {
            if (d.getPatient() != null && d.getPatient().getPatientId() == patientId) {
                record.addDiagnosis(d);
            }
        }

        List<Treatment> treatments = treatmentService.getTreatmentsByPatientId(patientId);
        for (Treatment t : treatments) {
            record.addTreatment(t);
        }

        return record;
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordDAO.getAllMedicalRecords();
    }

    public boolean deleteMedicalRecord(int id) {
        return medicalRecordDAO.deleteMedicalRecord(id);
    }
}