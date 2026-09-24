package hospital.dao;

import hospital.models.Department;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentDAO {
    private final List<Department> departmentList = new ArrayList<>();
    private int idCounter = 1;

    public DepartmentDAO() {
        // Seed default initial data
        save(new Department(idCounter++, "Cardiology", "Heart and vascular system care", "Building A, Floor 2"));
        save(new Department(idCounter++, "Neurology", "Brain and nervous system treatment", "Building B, Floor 3"));
        save(new Department(idCounter++, "Pediatrics", "Medical care for infants and children", "Building A, Floor 1"));
    }

    public Department save(Department department) {
        if (department.getId() <= 0) {
            department.setId(idCounter++);
        }
        departmentList.add(department);
        return department;
    }

    public List<Department> findAll() {
        return new ArrayList<>(departmentList);
    }

    public Optional<Department> findById(int id) {
        return departmentList.stream()
                .filter(d -> d.getId() == id)
                .findFirst();
    }

    public boolean update(Department department) {
        Optional<Department> existing = findById(department.getId());
        if (existing.isPresent()) {
            Department d = existing.get();
            d.setName(department.getName());
            d.setDescription(department.getDescription());
            d.setLocation(department.getLocation());
            return true;
        }
        return false;
    }

    public boolean deleteById(int id) {
        return departmentList.removeIf(d -> d.getId() == id);
    }
}