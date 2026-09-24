package hospital.services;

import static hospital.models.StaffRole.DOCTOR;
import static hospital.models.StaffRole.NURSE;
import static hospital.models.StaffRole.STAFF;
import static hospital.models.StaffRole.PHARMACIST;
import static hospital.models.StaffRole.LABORATORY_TECHNICIAN;

import hospital.models.StaffRole;
import hospital.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        // Adding default sample users for testing
        User u1 = new User("John Jones", "12345", DOCTOR);
        User u2 = new User("Mary Godwill", "12345", NURSE);
        User u3 = new User("Nigel Mista", "12345", STAFF);

        // Ensure active flag is true for all sample users
        u1.setActive(true);
        u2.setActive(true);
        u3.setActive(true);

        users.add(u1);
        users.add(u2);
        users.add(u3);
    }
    

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) 
                    && user.getPassword().equals(password) 
                    && user.isActive()) {
                return user; // Return user object if active and credentials match
            }
        }
        return null; // Return null if authentication fails or account is deactivated
    }

    // =========================================================
    // STAFF ACCOUNT MANAGEMENT METHODS
    // =========================================================

    public boolean createUser(String username, String password, StaffRole role) {
        if (findUserByUsername(username) != null) {
            return false; // Username already exists
        }
        User newUser = new User(username, password, role);
        newUser.setActive(true);
        users.add(newUser);
        return true;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean updateUserRole(String username, StaffRole newRole) {
        User user = findUserByUsername(username);
        if (user != null) {
            user.setRole(newRole);
            return true;
        }
        return false;
    }

    public boolean setAccountStatus(String username, boolean activeStatus) {
        User user = findUserByUsername(username);
        if (user != null) {
            user.setActive(activeStatus);
            return true;
        }
        return false;
    }

    public boolean resetPassword(String username, String newPassword) {
        User user = findUserByUsername(username);
        if (user != null) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }
}