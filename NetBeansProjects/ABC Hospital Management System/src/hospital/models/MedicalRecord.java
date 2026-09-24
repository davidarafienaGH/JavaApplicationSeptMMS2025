
package hospital.models;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class MedicalRecord {
    private int id;
    private Patient patient;
    private LocalDate createdDate;
    
    private List<Diagnosis> diagnoses = new ArrayList<>();
    private List<Treatment> treatments = new ArrayList<>();
    private List<LaboratoryTest> laboratoryTests = new ArrayList<>();
    private List<Prescription> prescriptions = new ArrayList<>();
    private List<Admission> admissions = new ArrayList<>();
    
    public MedicalRecord(){
        
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }
    
    public void addDiagnosis(Diagnosis diagnosis){
        diagnoses.add(diagnosis);
    }
    
    public void removeDiagnosis(Diagnosis diagnosis){
        diagnoses.remove(diagnosis);
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }
    
    public void addTreatment(Treatment treatment){
        treatments.add(treatment);
    }
    
    public void removeTreatment(Treatment treatment){
        treatments.remove(treatment);
    }

    public List<LaboratoryTest> getLaboratoryTests() {
        return laboratoryTests;
    }
    
    public void addLaboratoryTest(LaboratoryTest laboratoryTest){
        laboratoryTests.add(laboratoryTest);
    }
    
    public void removeLaboratoryTest(LaboratoryTest laboratoryTest){
        laboratoryTests.remove(laboratoryTest);
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }
    
    public void addPrescriptions(Prescription prescription){
        prescriptions.add(prescription);
    }
    
        public void removePrescriptions(Prescription prescription){
        prescriptions.remove(prescription);
    }

    public List<Admission> getAdmissions() {
        return admissions;
    }
    
    public void addAdmission(Admission admission){
        admissions.add(admission);
    }
    
    public void removeAdmission(Admission admission){
        admissions.remove(admission);
    }
    
    
}

