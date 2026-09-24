package hospital.dao;

import hospital.models.Bed;
import java.util.ArrayList;
import java.util.List;

public class BedDAO {
    private static final List<Bed> beds = new ArrayList<>();
    private static int idCounter = 1;

    public boolean addBed(Bed bed) {
        bed.setId(idCounter++);
        return beds.add(bed);
    }

    public List<Bed> getAllBeds() {
        return new ArrayList<>(beds);
    }

    public Bed getBedById(int id) {
        for (Bed bed : beds) {
            if (bed.getId() == id) {
                return bed;
            }
        }
        return null;
    }

    public List<Bed> getBedsByRoom(int roomId) {
        List<Bed> result = new ArrayList<>();
        for (Bed b : beds) {
            if (b.getRoom() != null && b.getRoom().getId() == roomId) {
                result.add(b);
            }
        }
        return result;
    }

    public boolean updateBed(Bed bed) {
        for (int i = 0; i < beds.size(); i++) {
            if (beds.get(i).getId() == bed.getId()) {
                beds.set(i, bed);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBed(int id) {
        return beds.removeIf(b -> b.getId() == id);
    }

    public List<Bed> getAvailableBeds() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}