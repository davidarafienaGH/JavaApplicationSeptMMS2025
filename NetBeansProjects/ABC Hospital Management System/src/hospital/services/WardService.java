package hospital.services;

import hospital.dao.WardDAO;
import hospital.models.Ward;
import java.util.List;

public class WardService {
    private final WardDAO wardDAO = new WardDAO();

    public boolean addWard(Ward ward) {
        return wardDAO.addWard(ward);
    }

    public List<Ward> getAllWards() {
        return wardDAO.getAllWards();
    }

    public Ward getWardById(int id) {
        return wardDAO.getWardById(id);
    }

    public boolean updateWard(Ward ward) {
        return wardDAO.updateWard(ward);
    }

    public boolean deleteWard(int id) {
        return wardDAO.deleteWard(id);
    }
}