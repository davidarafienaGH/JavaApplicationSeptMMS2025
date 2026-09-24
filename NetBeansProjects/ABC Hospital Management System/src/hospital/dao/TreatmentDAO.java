package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Diagnosis;
import hospital.models.Doctor;
import hospital.models.Patient;
import hospital.models.Treatment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAO {

    public boolean createTreatment(Treatment treatment) {
        String sql = "INSERT INTO Treatment (patient_id, doctor_id, diagnosis_id, treatment_date, treatment_name, description, notes, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, treatment.getPatient().getPatientId());
            stmt.setInt(2, treatment.getDoctor().getId());
            
            if (treatment.getDiagnosis() != null && treatment.getDiagnosis().getId() > 0) {
                stmt.setInt(3, treatment.getDiagnosis().getId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            stmt.setDate(4, Date.valueOf(treatment.getTreatmentDate()));
            stmt.setString(5, treatment.getTreatmentName());
            stmt.setString(6, treatment.getDescription());
            stmt.setString(7, treatment.getNotes());
            stmt.setString(8, treatment.getStatus());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error creating treatment: " + e.getMessage());
        }
        return false;
    }

    public List<Treatment> getAllTreatments() {
        List<Treatment> treatments = new ArrayList<>();
        String sql = "SELECT * FROM Treatment";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                treatments.add(mapResultSetToTreatment(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching treatments: " + e.getMessage());
        }
        return treatments;
    }

    public Treatment getTreatmentById(int id) {
        String sql = "SELECT * FROM Treatment WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTreatment(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding treatment: " + e.getMessage());
        }
        return null;
    }

    public List<Treatment> getTreatmentsByPatientId(int patientId) {
        List<Treatment> treatments = new ArrayList<>();
        String sql = "SELECT * FROM Treatment WHERE patient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    treatments.add(mapResultSetToTreatment(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching patient treatments: " + e.getMessage());
        }
        return treatments;
    }

    public boolean updateTreatment(Treatment treatment) {
        String sql = "UPDATE Treatment SET patient_id = ?, doctor_id = ?, diagnosis_id = ?, treatment_date = ?, treatment_name = ?, description = ?, notes = ?, status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, treatment.getPatient().getPatientId());
            stmt.setInt(2, treatment.getDoctor().getId());

            if (treatment.getDiagnosis() != null && treatment.getDiagnosis().getId() > 0) {
                stmt.setInt(3, treatment.getDiagnosis().getId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            stmt.setDate(4, Date.valueOf(treatment.getTreatmentDate()));
            stmt.setString(5, treatment.getTreatmentName());
            stmt.setString(6, treatment.getDescription());
            stmt.setString(7, treatment.getNotes());
            stmt.setString(8, treatment.getStatus());
            stmt.setInt(9, treatment.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating treatment: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteTreatment(int id) {
        String sql = "DELETE FROM Treatment WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting treatment: " + e.getMessage());
        }
        return false;
    }

    private Treatment mapResultSetToTreatment(ResultSet rs) throws SQLException {
        Treatment treatment = new Treatment();

        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("patient_id"));
        treatment.setPatient(patient);

        Doctor doctor = new Doctor();
        doctor.setId(rs.getInt("doctor_id"));
        treatment.setDoctor(doctor);

        int diagnosisId = rs.getInt("diagnosis_id");
        if (!rs.wasNull()) {
            Diagnosis diagnosis = new Diagnosis();
            // Assuming Diagnosis model has an setId or constructor
            treatment.setDiagnosis(diagnosis);
        }

        Date date = rs.getDate("treatment_date");
        if (date != null) {
            treatment.setTreatmentDate(date.toLocalDate());
        }

        treatment.setTreatmentName(rs.getString("treatment_name"));
        treatment.setDescription(rs.getString("description"));
        treatment.setNotes(rs.getString("notes"));
        treatment.setStatus(rs.getString("status"));

        return treatment;
    }
}