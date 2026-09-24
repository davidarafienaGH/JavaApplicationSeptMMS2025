package hospital.services;

import hospital.dao.PharmacistDAO;
import hospital.models.Pharmacist;

import java.util.List;

public class PharmacistService {

    private final PharmacistDAO pharmacistDAO;

    public PharmacistService() {
        this.pharmacistDAO = new PharmacistDAO();
    }

    public boolean addPharmacist(Pharmacist pharmacist) {
        if (pharmacist == null) {
            System.out.println("Pharmacist record cannot be null.");
            return false;
        }
        if (pharmacist.getFirstName() == null || pharmacist.getFirstName().trim().isEmpty() ||
            pharmacist.getLastName() == null || pharmacist.getLastName().trim().isEmpty()) {
            System.out.println("First and Last name are required.");
            return false;
        }
        if (pharmacist.getLicenseNumber() == null || pharmacist.getLicenseNumber().trim().isEmpty()) {
            System.out.println("License number is required.");
            return false;
        }
        return pharmacistDAO.addPharmacist(pharmacist);
    }

    public Pharmacist getPharmacistById(int staffId) {
        if (staffId <= 0) {
            System.out.println("Invalid Staff ID.");
            return null;
        }
        return pharmacistDAO.getPharmacistById(staffId);
    }

    public List<Pharmacist> getAllPharmacists() {
        return pharmacistDAO.getAllPharmacists();
    }

    public boolean updatePharmacist(Pharmacist pharmacist) {
        if (pharmacist == null || pharmacist.getStaffId() <= 0) {
            System.out.println("Invalid pharmacist record.");
            return false;
        }
        return pharmacistDAO.updatePharmacist(pharmacist);
    }

    public boolean deletePharmacist(int staffId) {
        if (staffId <= 0) {
            System.out.println("Invalid Staff ID.");
            return false;
        }
        return pharmacistDAO.deletePharmacist(staffId);
    }
}