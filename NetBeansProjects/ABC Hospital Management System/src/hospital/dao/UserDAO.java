package hospital.dao;

import hospital.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hospital.models.User;
import hospital.models.StaffRole;

public class UserDAO {

    public static User authenticate(String username, String password) {
        String sql = "SELECT u.UserId, u.Username, u.Role, u.IsActive " +
                     "FROM Users u " +
                     "WHERE u.Username = ? AND u.PasswordHash = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("UserId"));
                    user.setUsername(rs.getString("Username"));

                    // Map string from Database to your StaffRole enum
                    String roleStr = rs.getString("Role");
                    if (roleStr != null) {
                        try {
                            user.setRole(StaffRole.valueOf(roleStr.trim().toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            user.setRole(StaffRole.STAFF); // Default fallback
                        }
                    } else {
                        user.setRole(StaffRole.STAFF);
                    }

                    user.setActive(rs.getBoolean("IsActive"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Database Error during authentication: " + e.getMessage());
        }
        return null;
    }
}