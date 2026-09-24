package hospital.userview;

import hospital.models.Bed;
import java.util.List;

public class BedView {
    public void displayBeds(List<Bed> beds) {
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("                         BED LIST                            ");
        System.out.println("-------------------------------------------------------------");
        if (beds == null || beds.isEmpty()) {
            System.out.println("No beds found.");
            return;
        }
        System.out.printf("%-5s | %-12s | %-12s | %-10s%n", "ID", "Bed Number", "Room Number", "Status");
        System.out.println("-------------------------------------------------------------");
        for (Bed b : beds) {
            String roomNum = (b.getRoom() != null) ? b.getRoom().getRoomNumber() : "N/A";
            String status = b.isOccupied() ? "Occupied" : "Available";
            System.out.printf("%-5d | %-12s | %-12s | %-10s%n",
                    b.getId(), b.getBedNumber(), roomNum, status);
        }
        System.out.println("-------------------------------------------------------------");
    }

    public void displayBed(Bed bed) {
        if (bed == null) {
            System.out.println("Bed record is empty.");
            return;
        }
        System.out.println("\n[ Bed Details ]");
        System.out.println("ID         : " + bed.getId());
        System.out.println("Bed Number : " + bed.getBedNumber());
        System.out.println("Room Number: " + ((bed.getRoom() != null) ? bed.getRoom().getRoomNumber() : "N/A"));
        System.out.println("Status     : " + (bed.isOccupied() ? "Occupied" : "Available"));
    }
}