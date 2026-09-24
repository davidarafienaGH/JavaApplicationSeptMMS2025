package hospital.services;

import hospital.dao.DepartmentDAO;
import hospital.models.Department;

import java.util.List;

public class DepartmentService {
    private final DepartmentDAO departmentDAO;

    public DepartmentService() {
        this.departmentDAO = new DepartmentDAO();
    }

    public Department addDepartment(Department department) {
        if (department.getName() == null || department.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be empty.");
        }
        return departmentDAO.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentDAO.findAll();
    }

    public Department getDepartmentById(int id) {
        return departmentDAO.findById(id).orElse(null);
    }

    public boolean updateDepartment(Department department) {
        if (department == null || department.getId() <= 0) {
            return false;
        }
        return departmentDAO.update(department);
    }

    public boolean deleteDepartment(int id) {
        return departmentDAO.deleteById(id);
    }
}