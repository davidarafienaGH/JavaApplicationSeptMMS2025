package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Department;
import hospital.models.Pharmacist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PharmacistDAO {

    public boolean addPharmacist(Pharmacist pharmacist) {
        String personSql = "INSERT INTO Person (FirstName, LastName, Gender, DateOfBirth, Phone, Email, Street, City, Country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String staffSql = "INSERT INTO Staff (PersonID, EmploymentDate, Salary, DepartmentID) VALUES (?, ?, ?, ?)";
        String pharmacistSql = "INSERT INTO Pharmacist (StaffID, Qualification, LicenseNumber) VALUES (?, ?, ?)";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            int personId;
            try (PreparedStatement stmt = conn.prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, pharmacist.getFirstName());
                stmt.setString(2, pharmacist.getLastName());
                stmt.setString(3, String.valueOf(pharmacist.getGender()));
                stmt.setObject(4, pharmacist.getDateOfBirth());
                stmt.setString(5, pharmacist.getPhone());
                stmt.setString(6, pharmacist.getEmail());
                stmt.setString(7, pharmacist.getStreet());
                stmt.setString(8, pharmacist.getCity());
                stmt.setString(9, pharmacist.getCountry());
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
                stmt.setObject(2, pharmacist.getEmploymentDate());
                stmt.setDouble(3, pharmacist.getSalary());

                if (pharmacist.getDepartment() != null && pharmacist.getDepartment().getId() > 0) {
                    stmt.setInt(4, pharmacist.getDepartment().getId());
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

            try (PreparedStatement stmt = conn.prepareStatement(pharmacistSql)) {
                stmt.setInt(1, staffId);
                stmt.setString(2, pharmacist.getQualification());
                stmt.setString(3, pharmacist.getLicenseNumber());
                stmt.executeUpdate();
            }

            conn.commit();
            pharmacist.setStaffId(staffId);
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Error adding pharmacist: " + e.getMessage());
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

    public Pharmacist getPharmacistById(int staffId) {
        String sql = "SELECT s.StaffID, s.EmploymentDate, s.Salary, ph.Qualification, ph.LicenseNumber, " +
                     "p.FirstName, p.LastName, p.Gender, p.DateOfBirth, p.Phone, p.Email, p.Street, p.City, p.Country, " +
                     "d.DepartmentID, d.Name AS DepartmentName " +
                     "FROM Staff s " +
                     "INNER JOIN Person p ON s.PersonID = p.PersonID " +
                     "INNER JOIN Pharmacist ph ON s.StaffID = ph.StaffID " +
                     "LEFT JOIN Department d ON s.DepartmentID = d.DepartmentID " +
                     "WHERE s.StaffID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, staffId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapPharmacist(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving pharmacist: " + e.getMessage());
        }
        return null;
    }

    public List<Pharmacist> getAllPharmacists() {
        List<Pharmacist> list = new ArrayList<>();
        String sql = "SELECT s.StaffID, s.EmploymentDate, s.Salary, ph.Qualification, ph.LicenseNumber, " +
                     "p.FirstName, p.LastName, p.Gender, p.DateOfBirth, p.Phone, p.Email, p.Street, p.City, p.Country, " +
                     "d.DepartmentID, d.Name AS DepartmentName " +
                     "FROM Staff s " +
                     "INNER JOIN Person p ON s.PersonID = p.PersonID " +
                     "INNER JOIN Pharmacist ph ON s.StaffID = ph.StaffID " +
                     "LEFT JOIN Department d ON s.DepartmentID = d.DepartmentID " +
                     "ORDER BY p.LastName, p.FirstName";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapPharmacist(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving pharmacists: " + e.getMessage());
        }
        return list;
    }

    public boolean updatePharmacist(Pharmacist pharmacist) {
        String personSql = "UPDATE p " +
                           "SET p.FirstName = ?, p.LastName = ?, p.Phone = ?, p.Email = ? " +
                           "FROM Person p " +
                           "INNER JOIN Staff s ON p.PersonID = s.PersonID " +
                           "WHERE s.StaffID = ?";

        String staffSql = "UPDATE Staff SET Salary = ?, DepartmentID = ? WHERE StaffID = ?";
        String pharmacistSql = "UPDATE Pharmacist SET Qualification = ?, LicenseNumber = ? WHERE StaffID = ?";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(personSql)) {
                stmt.setString(1, pharmacist.getFirstName());
                stmt.setString(2, pharmacist.getLastName());
                stmt.setString(3, pharmacist.getPhone());
                stmt.setString(4, pharmacist.getEmail());
                stmt.setInt(5, pharmacist.getStaffId());
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(staffSql)) {
                stmt.setDouble(1, pharmacist.getSalary());
                if (pharmacist.getDepartment() != null && pharmacist.getDepartment().getId() > 0) {
                    stmt.setInt(2, pharmacist.getDepartment().getId());
                } else {
                    stmt.setNull(2, Types.INTEGER);
                }
                stmt.setInt(3, pharmacist.getStaffId());
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(pharmacistSql)) {
                stmt.setString(1, pharmacist.getQualification());
                stmt.setString(2, pharmacist.getLicenseNumber());
                stmt.setInt(3, pharmacist.getStaffId());
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
            System.err.println("Error updating pharmacist: " + e.getMessage());
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

    public boolean deletePharmacist(int staffId) {
        String sql = "DELETE FROM Staff WHERE StaffID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, staffId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting pharmacist: " + e.getMessage());
            return false;
        }
    }

    private Pharmacist mapPharmacist(ResultSet rs) throws SQLException {
        Pharmacist ph = new Pharmacist();
        ph.setStaffId(rs.getInt("StaffID"));
        ph.setFirstName(rs.getString("FirstName"));
        ph.setLastName(rs.getString("LastName"));

        String genderStr = rs.getString("Gender");
        if (genderStr != null && !genderStr.isEmpty()) {
            ph.setGender(genderStr.charAt(0));
        }

        Date dob = rs.getDate("DateOfBirth");
        if (dob != null) {
            ph.setDateOfBirth(dob.toLocalDate());
        }

        ph.setPhone(rs.getString("Phone"));
        ph.setEmail(rs.getString("Email"));
        ph.setStreet(rs.getString("Street"));
        ph.setCity(rs.getString("City"));
        ph.setCountry(rs.getString("Country"));

        Date empDate = rs.getDate("EmploymentDate");
        if (empDate != null) {
            ph.setEmploymentDate(empDate.toLocalDate());
        }

        ph.setSalary(rs.getDouble("Salary"));
        ph.setQualification(rs.getString("Qualification"));
        ph.setLicenseNumber(rs.getString("LicenseNumber"));

        int deptId = rs.getInt("DepartmentID");
        if (!rs.wasNull()) {
            Department dept = new Department();
            dept.setId(deptId);
            dept.setName(rs.getString("DepartmentName"));
            ph.setDepartment(dept);
        }

        return ph;
    }
}