package hospital.dao;

import hospital.models.Ward;
import java.util.ArrayList;
import java.util.List;

public class WardDAO {
    private static final List<Ward> wards = new ArrayList<>();
    private static int idCounter = 1;

    public boolean addWard(Ward ward) {
        ward.setId(idCounter++);
        return wards.add(ward);
    }

    public List<Ward> getAllWards() {
        return new ArrayList<>(wards);
    }

    public Ward getWardById(int id) {
        for (Ward ward : wards) {
            if (ward.getId() == id) {
                return ward;
            }
        }
        return null;
    }

    public boolean updateWard(Ward ward) {
        for (int i = 0; i < wards.size(); i++) {
            if (wards.get(i).getId() == ward.getId()) {
                wards.set(i, ward);
                return true;
            }
        }
        return false;
    }

    public boolean deleteWard(int id) {
        return wards.removeIf(w -> w.getId() == id);
    }
}