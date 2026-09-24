package hospital.services;

import hospital.dao.RoomDAO;
import hospital.models.Room;
import java.util.List;

public class RoomService {
    private final RoomDAO roomDAO = new RoomDAO();

    public boolean addRoom(Room room) {
        return roomDAO.addRoom(room);
    }

    public List<Room> getAllRooms() {
        return roomDAO.getAllRooms();
    }

    public Room getRoomById(int id) {
        return roomDAO.getRoomById(id);
    }

    public List<Room> getRoomsByWard(int wardId) {
        return roomDAO.getRoomsByWard(wardId);
    }

    public boolean updateRoom(Room room) {
        return roomDAO.updateRoom(room);
    }

    public boolean deleteRoom(int id) {
        return roomDAO.deleteRoom(id);
    }
}