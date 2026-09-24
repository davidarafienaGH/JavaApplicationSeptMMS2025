package hospital.userview;

import hospital.models.Ward;
import java.util.List;

public class WardView {
    public void displayWards(List<Ward> wards) {
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("                         WARD LIST                           ");
        System.out.println("-------------------------------------------------------------");
        if (wards == null || wards.isEmpty()) {
            System.out.println("No wards found.");
            return;
        }
        System.out.printf("%-5s | %-20s | %-15s | %-10s%n", "ID", "Ward Name", "Type", "Capacity");
        System.out.println("-------------------------------------------------------------");
        for (Ward w : wards) {
            System.out.printf("%-5d | %-20s | %-15s | %-10d%n",
                    w.getId(), w.getName(), w.getWardType(), w.getCapacity());
        }
        System.out.println("-------------------------------------------------------------");
    }

    public void displayWard(Ward ward) {
        if (ward == null) {
            System.out.println("Ward record is empty.");
            return;
        }
        System.out.println("\n[ Ward Details ]");
        System.out.println("ID        : " + ward.getId());
        System.out.println("Name      : " + ward.getName());
        System.out.println("Type      : " + ward.getWardType());
        System.out.println("Capacity  : " + ward.getCapacity());
    }
}