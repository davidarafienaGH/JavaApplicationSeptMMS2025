package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Medication;
import hospital.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicationDAO {

    public boolean createMedication(Medication medication) {
        String sql = "INSERT INTO Medication (name, description, dosageForm, price, quantityInStock) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medication.getName());
            stmt.setString(2, medication.getDescription());
            stmt.setString(3, medication.getDosageForm());
            stmt.setDouble(4, medication.getPrice());
            stmt.setInt(5, medication.getQuantityInStock());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Medication getMedicationById(int id) {
        String sql = "SELECT * FROM Medication WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToMedication(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Medication> getAllMedications() {
        List<Medication> list = new ArrayList<>();
        String sql = "SELECT * FROM Medication";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToMedication(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateMedication(Medication medication) {
        String sql = "UPDATE Medication SET name = ?, description = ?, dosageForm = ?, price = ?, quantityInStock = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medication.getName());
            stmt.setString(2, medication.getDescription());
            stmt.setString(3, medication.getDosageForm());
            stmt.setDouble(4, medication.getPrice());
            stmt.setInt(5, medication.getQuantityInStock());
            stmt.setInt(6, medication.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMedication(int id) {
        String sql = "DELETE FROM Medication WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Medication mapResultSetToMedication(ResultSet rs) throws SQLException {
        Medication m = new Medication();
        m.setName(rs.getString("name"));
        m.setDescription(rs.getString("description"));
        m.setDosageForm(rs.getString("dosageForm"));
        m.setPrice(rs.getDouble("price"));
        m.setQuantityInStock(rs.getInt("quantityInStock"));
        return m;
    }
}