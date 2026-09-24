package hospital.dao;

import hospital.models.Room;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private static final List<Room> rooms = new ArrayList<>();
    private static int idCounter = 1;

    public boolean addRoom(Room room) {
        room.setId(idCounter++);
        return rooms.add(room);
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    public Room getRoomById(int id) {
        for (Room room : rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }

    public List<Room> getRoomsByWard(int wardId) {
        List<Room> result = new ArrayList<>();
        for (Room r : rooms) {
            if (r.getWard() != null && r.getWard().getId() == wardId) {
                result.add(r);
            }
        }
        return result;
    }

    public boolean updateRoom(Room room) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getId() == room.getId()) {
                rooms.set(i, room);
                return true;
            }
        }
        return false;
    }

    public boolean deleteRoom(int id) {
        return rooms.removeIf(r -> r.getId() == id);
    }
}