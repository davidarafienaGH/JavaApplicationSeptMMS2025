package hospital.userview;

import hospital.models.LaboratoryTechnician;

import java.util.List;

public class LaboratoryTechnicianView {

    public void displayTechnicians(List<LaboratoryTechnician> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("\nNo laboratory technicians found.");
            return;
        }

        System.out.println("\n==========================================================================================================================");
        System.out.printf("%-8s %-25s %-20s %-20s %-20s %-15s%n",
                "STAFF ID", "NAME", "QUALIFICATION", "LICENSE NO", "PHONE", "DEPARTMENT");
        System.out.println("==========================================================================================================================");

        for (LaboratoryTechnician tech : list) {
            String name = tech.getFirstName() + " " + tech.getLastName();
            String dept = tech.getDepartment() != null ? tech.getDepartment().getName() : "N/A";
            String qual = tech.getQualification() != null ? tech.getQualification() : "N/A";
            String lic = tech.getLicenseNumber() != null ? tech.getLicenseNumber() : "N/A";

            System.out.printf("%-8d %-25s %-20s %-20s %-20s %-15s%n",
                    tech.getStaffId(), name, qual, lic, tech.getPhone(), dept);
        }
        System.out.println("==========================================================================================================================");
    }

    public void displayTechnician(LaboratoryTechnician tech) {
        if (tech == null) {
            System.out.println("\nLaboratory technician not found.");
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("        LABORATORY TECHNICIAN DETAILS         ");
        System.out.println("==============================================");
        System.out.println("Staff ID       : " + tech.getStaffId());
        System.out.println("Name           : " + tech.getFirstName() + " " + tech.getLastName());
        System.out.println("Gender         : " + tech.getGender());
        System.out.println("DOB            : " + tech.getDateOfBirth());
        System.out.println("Phone          : " + tech.getPhone());
        System.out.println("Email          : " + tech.getEmail());
        System.out.println("Qualification  : " + (tech.getQualification() != null ? tech.getQualification() : "N/A"));
        System.out.println("License Number : " + (tech.getLicenseNumber() != null ? tech.getLicenseNumber() : "N/A"));
        System.out.println("Employment Date: " + tech.getEmploymentDate());
        System.out.println("Salary         : $" + String.format("%.2f", tech.getSalary()));
        System.out.println("Department     : " + (tech.getDepartment() != null ? tech.getDepartment().getName() : "N/A"));
        System.out.println("==============================================");
    }

    public void displayTechCreated() {
        System.out.println("\nLaboratory technician added successfully.");
    }

    public void displayTechUpdated() {
        System.out.println("\nLaboratory technician updated successfully.");
    }

    public void displayTechDeleted() {
        System.out.println("\nLaboratory technician deleted successfully.");
    }
}
