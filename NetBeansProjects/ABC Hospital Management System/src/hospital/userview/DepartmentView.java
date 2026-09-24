package hospital.userview;

import hospital.models.Department;
import java.util.List;

public class DepartmentView {

    public void displayDepartmentList(List<Department> departments) {
        if (departments == null || departments.isEmpty()) {
            System.out.println("No departments found.");
            return;
        }
        System.out.println("\n----------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-30s %-20s%n", "ID", "Name", "Description", "Location");
        System.out.println("----------------------------------------------------------------------------------");
        for (Department d : departments) {
            System.out.printf("%-5d %-20s %-30s %-20s%n",
                    d.getId(),
                    truncate(d.getName(), 18),
                    truncate(d.getDescription(), 28),
                    truncate(d.getLocation(), 18));
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    public void displayDepartmentDetails(Department department) {
        if (department == null) {
            System.out.println("Department not found.");
            return;
        }
        System.out.println("\n--- Department Details ---");
        System.out.println("ID         : " + department.getId());
        System.out.println("Name       : " + department.getName());
        System.out.println("Description: " + department.getDescription());
        System.out.println("Location   : " + department.getLocation());
    }

    private String truncate(String str, int width) {
        if (str == null) return "";
        if (str.length() > width) {
            return str.substring(0, width - 3) + "...";
        }
        return str;
    }
}