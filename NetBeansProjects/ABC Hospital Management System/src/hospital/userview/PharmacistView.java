package hospital.userview;

import hospital.models.Pharmacist;

import java.util.List;

public class PharmacistView {

    public void displayPharmacists(List<Pharmacist> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("\nNo pharmacists found.");
            return;
        }

        System.out.println("\n==========================================================================================================================");
        System.out.printf("%-8s %-25s %-20s %-20s %-20s %-15s%n",
                "STAFF ID", "NAME", "QUALIFICATION", "LICENSE NO", "PHONE", "DEPARTMENT");
        System.out.println("==========================================================================================================================");

        for (Pharmacist ph : list) {
            String name = ph.getFirstName() + " " + ph.getLastName();
            String dept = (ph.getDepartment() != null) ? ph.getDepartment().getName() : "N/A";
            String qual = (ph.getQualification() != null) ? ph.getQualification() : "N/A";
            String lic = (ph.getLicenseNumber() != null) ? ph.getLicenseNumber() : "N/A";

            System.out.printf("%-8d %-25s %-20s %-20s %-20s %-15s%n",
                    ph.getStaffId(), name, qual, lic, ph.getPhone(), dept);
        }
        System.out.println("==========================================================================================================================");
    }

    public void displayPharmacist(Pharmacist ph) {
        if (ph == null) {
            System.out.println("\nPharmacist not found.");
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("              PHARMACIST DETAILS              ");
        System.out.println("==============================================");
        System.out.println("Staff ID       : " + ph.getStaffId());
        System.out.println("Name           : " + ph.getFirstName() + " " + ph.getLastName());
        System.out.println("Gender         : " + ph.getGender());
        System.out.println("DOB            : " + ph.getDateOfBirth());
        System.out.println("Phone          : " + ph.getPhone());
        System.out.println("Email          : " + ph.getEmail());
        System.out.println("Qualification  : " + ph.getQualification());
        System.out.println("License Number : " + ph.getLicenseNumber());
        System.out.println("Employment Date: " + ph.getEmploymentDate());
        System.out.printf("Salary         : $%.2f%n", ph.getSalary());
        System.out.println("Department     : " + ((ph.getDepartment() != null) ? ph.getDepartment().getName() : "N/A"));
        System.out.println("==============================================");
    }

    public void displayPharmacistCreated() {
        System.out.println("\nPharmacist added successfully.");
    }

    public void displayPharmacistUpdated() {
        System.out.println("\nPharmacist updated successfully.");
    }

    public void displayPharmacistDeleted() {
        System.out.println("\nPharmacist deleted successfully.");
    }
}