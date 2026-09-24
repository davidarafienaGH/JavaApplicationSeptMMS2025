package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Diagnosis;
import hospital.models.Doctor;
import hospital.models.Patient;
import hospital.database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisDAO {

    public boolean createDiagnosis(Diagnosis diagnosis) {
        String sql = "INSERT INTO Diagnosis (patient_id, doctor_id, diagnosis_date, condition_name, description, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, diagnosis.getPatient().getPatientId());
            stmt.setInt(2, diagnosis.getDoctor().getId());
            stmt.setDate(3, Date.valueOf(diagnosis.getDiagnosisDate()));
            stmt.setString(4, diagnosis.getCondition());
            stmt.setString(5, diagnosis.getDescription());
            stmt.setString(6, diagnosis.getNotes());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Assuming Diagnosis model has an setId or constructor initialization
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error creating diagnosis: " + e.getMessage());
        }
        return false;
    }

    public List<Diagnosis> getAllDiagnoses() {
        List<Diagnosis> diagnoses = new ArrayList<>();
        String sql = "SELECT * FROM Diagnosis";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                diagnoses.add(mapResultSetToDiagnosis(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching diagnoses: " + e.getMessage());
        }
        return diagnoses;
    }

    public Diagnosis getDiagnosisById(int id) {
        String sql = "SELECT * FROM Diagnosis WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDiagnosis(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding diagnosis: " + e.getMessage());
        }
        return null;
    }

    public boolean updateDiagnosis(Diagnosis diagnosis) {
        String sql = "UPDATE Diagnosis SET patient_id = ?, doctor_id = ?, diagnosis_date = ?, condition_name = ?, description = ?, notes = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, diagnosis.getPatient().getPatientId());
            stmt.setInt(2, diagnosis.getDoctor().getId());
            stmt.setDate(3, Date.valueOf(diagnosis.getDiagnosisDate()));
            stmt.setString(4, diagnosis.getCondition());
            stmt.setString(5, diagnosis.getDescription());
            stmt.setString(6, diagnosis.getNotes());
            stmt.setInt(7, diagnosis.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating diagnosis: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteDiagnosis(int id) {
        String sql = "DELETE FROM Diagnosis WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting diagnosis: " + e.getMessage());
        }
        return false;
    }

    private Diagnosis mapResultSetToDiagnosis(ResultSet rs) throws SQLException {
        Diagnosis diagnosis = new Diagnosis();
        
        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("patient_id"));
        diagnosis.setPatient(patient);

        Doctor doctor = new Doctor();
        // Sets doctor id
        diagnosis.setDoctor(doctor);

        Date date = rs.getDate("diagnosis_date");
        if (date != null) {
            diagnosis.setDiagnosisDate(date.toLocalDate());
        }

        diagnosis.setCondition(rs.getString("condition_name"));
        diagnosis.setDescription(rs.getString("description"));
        diagnosis.setNotes(rs.getString("notes"));

        return diagnosis;
    }
}