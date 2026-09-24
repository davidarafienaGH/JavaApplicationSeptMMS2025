package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Doctor;
import hospital.models.Patient;
import hospital.models.Prescription;
import hospital.database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    public boolean createPrescription(Prescription prescription) {
        String sql = "INSERT INTO Prescription (PatientId, DoctorId, prescriptionDate) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, prescription.getPatient().getPatientId());
            stmt.setInt(2, prescription.getDoctor().getId());
            stmt.setDate(3, Date.valueOf(prescription.getPrescriptionDate()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        prescription.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Prescription getPrescriptionById(int id) {
        String sql = "SELECT * FROM Prescription WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToPrescription(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Prescription> getAllPrescriptions() {
        List<Prescription> list = new ArrayList<>();
        String sql = "SELECT * FROM Prescription";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToPrescription(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deletePrescription(int id) {
        String sql = "DELETE FROM Prescription WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Prescription mapResultSetToPrescription(ResultSet rs) throws SQLException {
        Prescription p = new Prescription();
        p.setId(rs.getInt("id"));

        // Placeholder assignment for relation mapping
        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("PatientId"));
        p.setPatient(patient);

        Doctor doctor = new Doctor();
        doctor.setId(rs.getInt("DoctorId"));
        p.setDoctor(doctor);

        Date date = rs.getDate("prescriptionDate");
        if (date != null) {
            p.setPrescriptionDate(date.toLocalDate());
        }
        return p;
    }
}