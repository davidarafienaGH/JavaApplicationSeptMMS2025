package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.MedicalRecord;
import hospital.models.Patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {

    public boolean createMedicalRecord(MedicalRecord record) {
        String sql = "INSERT INTO MedicalRecord (patient_id, created_date) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, record.getPatient().getPatientId());
            stmt.setDate(2, Date.valueOf(record.getCreatedDate()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Assuming MedicalRecord has an id setter via constructor or reflection
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error creating medical record: " + e.getMessage());
        }
        return false;
    }

    public MedicalRecord getMedicalRecordByPatientId(int patientId) {
        String sql = "SELECT * FROM MedicalRecord WHERE patient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMedicalRecord(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching medical record by patient ID: " + e.getMessage());
        }
        return null;
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM MedicalRecord";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                records.add(mapResultSetToMedicalRecord(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching medical records: " + e.getMessage());
        }
        return records;
    }

    public boolean deleteMedicalRecord(int id) {
        String sql = "DELETE FROM MedicalRecord WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting medical record: " + e.getMessage());
        }
        return false;
    }

    private MedicalRecord mapResultSetToMedicalRecord(ResultSet rs) throws SQLException {
        MedicalRecord record = new MedicalRecord();
        
        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("patient_id"));
        record.setPatient(patient);

        Date date = rs.getDate("created_date");
        if (date != null) {
            record.setCreatedDate(date.toLocalDate());
        }

        return record;
    }
}