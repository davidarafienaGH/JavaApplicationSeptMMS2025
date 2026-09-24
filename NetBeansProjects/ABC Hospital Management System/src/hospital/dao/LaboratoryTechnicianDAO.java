package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Department;
import hospital.models.LaboratoryTechnician;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class LaboratoryTechnicianDAO {

    public boolean addLaboratoryTechnician(LaboratoryTechnician tech) {
        String personSql = "INSERT INTO Person (FirstName, LastName, Gender, DateOfBirth, Phone, Email, Street, City, Country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String staffSql = "INSERT INTO Staff (PersonID, StaffRole, EmploymentDate, Salary, DepartmentID) VALUES (?, 'TECHNICIAN', ?, ?, ?)";
        String techSql = "INSERT INTO LaboratoryTechnician (StaffID, Qualification, LicenseNumber) VALUES (?, ?, ?)";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            int personId;
            try (PreparedStatement stmt = conn.prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, tech.getFirstName());
                stmt.setString(2, tech.getLastName());
                stmt.setString(3, String.valueOf(tech.getGender()));
                stmt.setObject(4, tech.getDateOfBirth());
                stmt.setString(5, tech.getPhone());
                stmt.setString(6, tech.getEmail());
                stmt.setString(7, tech.getStreet());
                stmt.setString(8, tech.getCity());
                stmt.setString(9, tech.getCountry());
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        personId = rs.getInt(1);
                    } else {
                        conn.rollback();
                        return false;
                    }
                }
            }

            int staffId;
            try (PreparedStatement stmt = conn.prepareStatement(staffSql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, personId);
                stmt.setObject(2, tech.getEmploymentDate());
                stmt.setDouble(3, tech.getSalary());

                if (tech.getDepartment() != null && tech.getDepartment().getId() > 0) {
                    stmt.setInt(4, tech.getDepartment().getId());
                } else {
                    stmt.setNull(4, Types.INTEGER);
                }
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        staffId = rs.getInt(1);
                    } else {
                        conn.rollback();
                        return false;
                    }
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(techSql)) {
                stmt.setInt(1, staffId);
                stmt.setString(2, tech.getQualification());
                stmt.setString(3, tech.getLicenseNumber());
                stmt.executeUpdate();
            }

            conn.commit();
            tech.setStaffId(staffId);
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Error adding laboratory technician: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public LaboratoryTechnician getLaboratoryTechnicianById(int staffId) {
        String sql = "SELECT s.StaffID, s.EmploymentDate, s.Salary, lt.Qualification, lt.LicenseNumber, " +
                     "p.FirstName, p.LastName, p.Gender, p.DateOfBirth, p.Phone, p.Email, p.Street, p.City, p.Country, " +
                     "d.DepartmentID, d.Name AS DepartmentName " +
                     "FROM Staff s " +
                     "INNER JOIN Person p ON s.PersonID = p.PersonID " +
                     "INNER JOIN LaboratoryTechnician lt ON s.StaffID = lt.StaffID " +
                     "LEFT JOIN Department d ON s.DepartmentID = d.DepartmentID " +
                     "WHERE s.StaffID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, staffId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapTechnician(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving technician: " + e.getMessage());
        }
        return null;
    }

    public List<LaboratoryTechnician> getAllLaboratoryTechnicians() {
        List<LaboratoryTechnician> list = new ArrayList<>();
        String sql = "SELECT s.StaffID, s.EmploymentDate, s.Salary, lt.Qualification, lt.LicenseNumber, " +
                     "p.FirstName, p.LastName, p.Gender, p.DateOfBirth, p.Phone, p.Email, p.Street, p.City, p.Country, " +
                     "d.DepartmentID, d.Name AS DepartmentName " +
                     "FROM Staff s " +
                     "INNER JOIN Person p ON s.PersonID = p.PersonID " +
                     "INNER JOIN LaboratoryTechnician lt ON s.StaffID = lt.StaffID " +
                     "LEFT JOIN Department d ON s.DepartmentID = d.DepartmentID " +
                     "ORDER BY p.LastName, p.FirstName";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapTechnician(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving laboratory technicians: " + e.getMessage());
        }
        return list;
    }

    public boolean updateLaboratoryTechnician(LaboratoryTechnician tech) {
        // SQL Server compatible UPDATE syntax
        String personSql = "UPDATE p " +
                           "SET p.FirstName = ?, p.LastName = ?, p.Phone = ?, p.Email = ? " +
                           "FROM Person p " +
                           "INNER JOIN Staff s ON p.PersonID = s.PersonID " +
                           "WHERE s.StaffID = ?";
                           
        String staffSql = "UPDATE Staff SET Salary = ?, DepartmentID = ? WHERE StaffID = ?";
        String techSql = "UPDATE LaboratoryTechnician SET Qualification = ?, LicenseNumber = ? WHERE StaffID = ?";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(personSql)) {
                stmt.setString(1, tech.getFirstName());
                stmt.setString(2, tech.getLastName());
                stmt.setString(3, tech.getPhone());
                stmt.setString(4, tech.getEmail());
                stmt.setInt(5, tech.getStaffId());
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(staffSql)) {
                stmt.setDouble(1, tech.getSalary());
                if (tech.getDepartment() != null && tech.getDepartment().getId() > 0) {
                    stmt.setInt(2, tech.getDepartment().getId());
                } else {
                    stmt.setNull(2, Types.INTEGER);
                }
                stmt.setInt(3, tech.getStaffId());
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(techSql)) {
                stmt.setString(1, tech.getQualification());
                stmt.setString(2, tech.getLicenseNumber());
                stmt.setInt(3, tech.getStaffId());
                stmt.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Error updating laboratory technician: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean deleteLaboratoryTechnician(int staffId) {
        String sql = "DELETE FROM Staff WHERE StaffID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, staffId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting technician: " + e.getMessage());
            return false;
        }
    }

    private LaboratoryTechnician mapTechnician(ResultSet rs) throws SQLException {
        LaboratoryTechnician tech = new LaboratoryTechnician();
        tech.setStaffId(rs.getInt("StaffID"));
        tech.setFirstName(rs.getString("FirstName"));
        tech.setLastName(rs.getString("LastName"));

        String genderStr = rs.getString("Gender");
        if (genderStr != null && !genderStr.isEmpty()) {
            tech.setGender(genderStr.charAt(0));
        }

        Date dob = rs.getDate("DateOfBirth");
        if (dob != null) {
            tech.setDateOfBirth(dob.toLocalDate());
        }

        tech.setPhone(rs.getString("Phone"));
        tech.setEmail(rs.getString("Email"));
        tech.setStreet(rs.getString("Street"));
        tech.setCity(rs.getString("City"));
        tech.setCountry(rs.getString("Country"));

        Date empDate = rs.getDate("EmploymentDate");
        if (empDate != null) {
            tech.setEmploymentDate(empDate.toLocalDate());
        }

        tech.setSalary(rs.getDouble("Salary"));
        tech.setQualification(rs.getString("Qualification"));
        tech.setLicenseNumber(rs.getString("LicenseNumber"));

        int deptId = rs.getInt("DepartmentID");
        if (!rs.wasNull()) {
            Department dept = new Department();
            dept.setId(deptId);
            dept.setName(rs.getString("DepartmentName"));
            tech.setDepartment(dept);
        }

        return tech;
    }
}