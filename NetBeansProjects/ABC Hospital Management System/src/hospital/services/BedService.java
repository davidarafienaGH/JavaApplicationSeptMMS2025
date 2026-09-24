package hospital.services;

import hospital.dao.BedDAO;
import hospital.models.Bed;
import java.util.ArrayList;
import java.util.List;

public class BedService {
    private final BedDAO bedDAO = new BedDAO();

    public boolean addBed(Bed bed) {
        return bedDAO.addBed(bed);
    }

    public List<Bed> getAllBeds() {
        return bedDAO.getAllBeds();
    }

    public Bed getBedById(int id) {
        return bedDAO.getBedById(id);
    }

    public List<Bed> getAvailableBeds() {
        List<Bed> available = new ArrayList<>();
        for (Bed b : bedDAO.getAllBeds()) {
            if (!b.isOccupied()) {
                available.add(b);
            }
        }
        return available;
    }

    public List<Bed> getOccupiedBeds() {
        List<Bed> occupied = new ArrayList<>();
        for (Bed b : bedDAO.getAllBeds()) {
            if (b.isOccupied()) {
                occupied.add(b);
            }
        }
        return occupied;
    }

    public boolean updateBed(Bed bed) {
        return bedDAO.updateBed(bed);
    }

    public boolean deleteBed(int id) {
        return bedDAO.deleteBed(id);
    }
}