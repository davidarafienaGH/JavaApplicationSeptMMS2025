package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Admission;
import hospital.models.Bed;
import hospital.models.Patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdmissionDAO {

    public boolean addAdmission(Admission admission) {
        String checkBedSql = "SELECT Occupied FROM Bed WHERE BedId = ?";
        String insertSql = "INSERT INTO Admission (PatientId, BedId, AdmissionDate, DischargeDate, Reason, Status) VALUES (?, ?, ?, ?, ?, ?)";
        String updateBedSql = "UPDATE Bed SET Occupied = 1 WHERE BedId = ?";

        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement checkStmt = connection.prepareStatement(checkBedSql)) {
                checkStmt.setInt(1, admission.getBed().getId());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        boolean occupied = rs.getBoolean("Occupied");
                        if (occupied) {
                            System.out.println("This bed is already occupied.");
                            connection.rollback();
                            return false;
                        }
                    } else {
                        System.out.println("Bed not found.");
                        connection.rollback();
                        return false;
                    }
                }
            }

            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setInt(1, admission.getPatient().getPatientId());
                insertStmt.setInt(2, admission.getBed().getId());
                insertStmt.setDate(3, Date.valueOf(admission.getAdmissionDate()));

                if (admission.getDischargeDate() != null) {
                    insertStmt.setDate(4, Date.valueOf(admission.getDischargeDate()));
                } else {
                    insertStmt.setNull(4, Types.DATE);
                }

                insertStmt.setString(5, admission.getReason());
                insertStmt.setString(6, admission.getStatus());

                int rows = insertStmt.executeUpdate();
                if (rows > 0) {
                    try (ResultSet keys = insertStmt.getGeneratedKeys()) {
                        if (keys.next()) {
                            admission.setId(keys.getInt(1));
                        }
                    }
                }
            }

            try (PreparedStatement updateBedStmt = connection.prepareStatement(updateBedSql)) {
                updateBedStmt.setInt(1, admission.getBed().getId());
                updateBedStmt.executeUpdate();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error adding admission: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Rollback error: " + rollbackEx.getMessage());
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeEx) {
                    System.out.println("Error closing connection: " + closeEx.getMessage());
                }
            }
        }
    }

    public Admission getAdmissionById(int admissionId) {
        String sql = "SELECT * FROM Admission WHERE AdmissionId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, admissionId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapAdmission(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving admission: " + e.getMessage());
        }
        return null;
    }

    public List<Admission> getAllAdmissions() {
        List<Admission> admissions = new ArrayList<>();
        String sql = "SELECT * FROM Admission ORDER BY AdmissionId DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                admissions.add(mapAdmission(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving admissions: " + e.getMessage());
        }
        return admissions;
    }

    public List<Admission> getAdmissionsByPatient(int patientId) {
        List<Admission> admissions = new ArrayList<>();
        String sql = "SELECT * FROM Admission WHERE PatientId = ? ORDER BY AdmissionDate DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    admissions.add(mapAdmission(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patient admissions: " + e.getMessage());
        }
        return admissions;
    }

    public boolean dischargePatient(int admissionId, LocalDate dischargeDate) {
        String getBedSql = "SELECT BedId FROM Admission WHERE AdmissionId = ?";
        String updateAdmissionSql = "UPDATE Admission SET DischargeDate = ?, Status = 'Discharged' WHERE AdmissionId = ? AND Status <> 'Discharged'";
        String releaseBedSql = "UPDATE Bed SET Occupied = 0 WHERE BedId = ?";

        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            int bedId = -1;
            try (PreparedStatement getBedStmt = connection.prepareStatement(getBedSql)) {
                getBedStmt.setInt(1, admissionId);
                try (ResultSet rs = getBedStmt.executeQuery()) {
                    if (rs.next()) {
                        bedId = rs.getInt("BedId");
                    } else {
                        System.out.println("Admission not found.");
                        connection.rollback();
                        return false;
                    }
                }
            }

            try (PreparedStatement updateStmt = connection.prepareStatement(updateAdmissionSql)) {
                updateStmt.setDate(1, Date.valueOf(dischargeDate));
                updateStmt.setInt(2, admissionId);
                int rows = updateStmt.executeUpdate();
                if (rows == 0) {
                    System.out.println("Patient is already discharged.");
                    connection.rollback();
                    return false;
                }
            }

            try (PreparedStatement releaseStmt = connection.prepareStatement(releaseBedSql)) {
                releaseStmt.setInt(1, bedId);
                releaseStmt.executeUpdate();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error discharging patient: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Rollback error: " + rollbackEx.getMessage());
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeEx) {
                    System.out.println("Error closing connection: " + closeEx.getMessage());
                }
            }
        }
    }

    public boolean deleteAdmission(int admissionId) {
        String getAdmissionSql = "SELECT BedId, Status FROM Admission WHERE AdmissionId = ?";
        String deleteSql = "DELETE FROM Admission WHERE AdmissionId = ?";
        String releaseBedSql = "UPDATE Bed SET Occupied = 0 WHERE BedId = ?";

        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            int bedId = -1;
            String status = "";

            try (PreparedStatement getStmt = connection.prepareStatement(getAdmissionSql)) {
                getStmt.setInt(1, admissionId);
                try (ResultSet rs = getStmt.executeQuery()) {
                    if (rs.next()) {
                        bedId = rs.getInt("BedId");
                        status = rs.getString("Status");
                    } else {
                        System.out.println("Admission not found.");
                        connection.rollback();
                        return false;
                    }
                }
            }

            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
                deleteStmt.setInt(1, admissionId);
                deleteStmt.executeUpdate();
            }

            if (!"Discharged".equalsIgnoreCase(status)) {
                try (PreparedStatement releaseStmt = connection.prepareStatement(releaseBedSql)) {
                    releaseStmt.setInt(1, bedId);
                    releaseStmt.executeUpdate();
                }
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error deleting admission: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Rollback error: " + rollbackEx.getMessage());
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeEx) {
                    System.out.println("Error closing connection: " + closeEx.getMessage());
                }
            }
        }
    }
    public boolean updateAdmission(Admission admission) {
    String sql = "UPDATE Admission SET PatientId = ?, BedId = ?, AdmissionDate = ?, DischargeDate = ?, Reason = ?, Status = ? WHERE AdmissionId = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, admission.getPatient().getPatientId());
        stmt.setInt(2, admission.getBed().getId());
        stmt.setDate(3, Date.valueOf(admission.getAdmissionDate()));
        stmt.setDate(4, admission.getDischargeDate() != null ? Date.valueOf(admission.getDischargeDate()) : null);
        stmt.setString(5, admission.getReason());
        stmt.setString(6, admission.getStatus());
        stmt.setInt(7, admission.getId());
        
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Error updating admission: " + e.getMessage());
        return false;
    }
}

    private Admission mapAdmission(ResultSet rs) throws SQLException {
        Admission admission = new Admission();
        admission.setId(rs.getInt("AdmissionId"));

        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("PatientId"));
        admission.setPatient(patient);

        Bed bed = new Bed();
        bed.setId(rs.getInt("BedId"));
        admission.setBed(bed);

        Date admissionDate = rs.getDate("AdmissionDate");
        if (admissionDate != null) {
            admission.setAdmissionDate(admissionDate.toLocalDate());
        }

        Date dischargeDate = rs.getDate("DischargeDate");
        if (dischargeDate != null) {
            admission.setDischargeDate(dischargeDate.toLocalDate());
        }

        admission.setReason(rs.getString("Reason"));
        admission.setStatus(rs.getString("Status"));

        return admission;
    }
}