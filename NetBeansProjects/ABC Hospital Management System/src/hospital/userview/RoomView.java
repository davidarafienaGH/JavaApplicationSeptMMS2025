package hospital.userview;

import hospital.models.Room;
import java.util.List;

public class RoomView {
    public void displayRooms(List<Room> rooms) {
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("                         ROOM LIST                           ");
        System.out.println("-------------------------------------------------------------");
        if (rooms == null || rooms.isEmpty()) {
            System.out.println("No rooms found.");
            return;
        }
        System.out.printf("%-5s | %-12s | %-15s | %-20s%n", "ID", "Room Number", "Type", "Ward Name");
        System.out.println("-------------------------------------------------------------");
        for (Room r : rooms) {
            String wardName = (r.getWard() != null) ? r.getWard().getName() : "N/A";
            System.out.printf("%-5d | %-12s | %-15s | %-20s%n",
                    r.getId(), r.getRoomNumber(), r.getRoomType(), wardName);
        }
        System.out.println("-------------------------------------------------------------");
    }

    public void displayRoom(Room room) {
        if (room == null) {
            System.out.println("Room record is empty.");
            return;
        }
        System.out.println("\n[ Room Details ]");
        System.out.println("ID         : " + room.getId());
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Room Type  : " + room.getRoomType());
        System.out.println("Ward       : " + ((room.getWard() != null) ? room.getWard().getName() : "N/A"));
    }
}