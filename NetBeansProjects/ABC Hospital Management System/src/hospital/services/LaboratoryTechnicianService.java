package hospital.services;

import hospital.dao.LaboratoryTechnicianDAO;
import hospital.models.LaboratoryTechnician;
import java.util.List;

public class LaboratoryTechnicianService {
    private final LaboratoryTechnicianDAO techDAO;

    public LaboratoryTechnicianService() {
        this.techDAO = new LaboratoryTechnicianDAO();
    }

    public boolean addLaboratoryTechnician(LaboratoryTechnician tech) {
        if (tech == null) {
            System.out.println("Technician record cannot be null.");
            return false;
        }
        if (tech.getFirstName() == null || tech.getFirstName().trim().isEmpty() ||
            tech.getLastName() == null || tech.getLastName().trim().isEmpty()) {
            System.out.println("First and Last name are required.");
            return false;
        }
        if (tech.getLicenseNumber() == null || tech.getLicenseNumber().trim().isEmpty()) {
            System.out.println("License number is required.");
            return false;
        }
        return techDAO.addLaboratoryTechnician(tech);
    }

    public LaboratoryTechnician getLaboratoryTechnicianById(int staffId) {
        if (staffId <= 0) {
            System.out.println("Invalid Staff ID.");
            return null;
        }
        return techDAO.getLaboratoryTechnicianById(staffId);
    }

    public List<LaboratoryTechnician> getAllLaboratoryTechnicians() {
        return techDAO.getAllLaboratoryTechnicians();
    }

    public boolean updateLaboratoryTechnician(LaboratoryTechnician tech) {
        if (tech == null || tech.getStaffId() <= 0) {
            System.out.println("Invalid technician record.");
            return false;
        }
        return techDAO.updateLaboratoryTechnician(tech);
    }

    public boolean deleteLaboratoryTechnician(int staffId) {
        if (staffId <= 0) {
            System.out.println("Invalid Staff ID.");
            return false;
        }
        return techDAO.deleteLaboratoryTechnician(staffId);
    }
}