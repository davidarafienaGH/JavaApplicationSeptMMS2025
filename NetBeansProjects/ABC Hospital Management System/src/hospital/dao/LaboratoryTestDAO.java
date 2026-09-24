package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.LaboratoryTest;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryTestDAO {

    // =========================================================
    // CREATE LABORATORY TEST
    // =========================================================

    public int create(LaboratoryTest test) {

        String sql = """
                INSERT INTO LaboratoryTest
                (
                    PatientId,
                    TechnicianId,
                    TestName,
                    TestDate,
                    Result,
                    ReferenceRange,
                    Status
                )
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            // Patient
            ps.setInt(
                    1,
                    test.getPatient().getPatientId()
            );

            // Laboratory Technician
            if (test.getTechnician() != null) {

                ps.setInt(
                        2,
                        test.getTechnician().getStaffId()
                );

            } else {

                ps.setNull(
                        2,
                        Types.INTEGER
                );
            }

            // Test name
            ps.setString(
                    3,
                    test.getTestName()
            );

            // Test date
            if (test.getTestDate() != null) {

                ps.setTimestamp(
                        4,
                        Timestamp.valueOf(
                                test.getTestDate()
                        )
                );

            } else {

                ps.setTimestamp(
                        4,
                        Timestamp.valueOf(
                                LocalDateTime.now()
                        )
                );
            }

            // Result
            ps.setString(
                    5,
                    test.getResult()
            );

            // Reference range
            ps.setString(
                    6,
                    test.getReferenceRange()
            );

            // Status
            ps.setString(
                    7,
                    test.getStatus()
            );

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                return 0;
            }

            // Get generated ID
            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {

                    int id = rs.getInt(1);

                    test.setId(id);

                    return id;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error creating laboratory test: "
                            + e.getMessage()
            );
        }

        return 0;
    }


    // =========================================================
    // GET ALL LABORATORY TESTS
    // =========================================================

    public List<LaboratoryTest> getAll() {

        List<LaboratoryTest> tests = new ArrayList<>();

        String sql = """
                SELECT
                    lt.Id,
                    lt.PatientId,
                    lt.TechnicianId,
                    lt.TestName,
                    lt.TestDate,
                    lt.Result,
                    lt.ReferenceRange,
                    lt.Status
                FROM LaboratoryTest lt
                ORDER BY lt.TestDate DESC
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                LaboratoryTest test = mapResultSet(rs);

                tests.add(test);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error retrieving laboratory tests: "
                            + e.getMessage()
            );
        }

        return tests;
    }


    // =========================================================
    // FIND LABORATORY TEST BY ID
    // =========================================================

    public LaboratoryTest findById(int id) {

        String sql = """
                SELECT
                    lt.Id,
                    lt.PatientId,
                    lt.TechnicianId,
                    lt.TestName,
                    lt.TestDate,
                    lt.Result,
                    lt.ReferenceRange,
                    lt.Status
                FROM LaboratoryTest lt
                WHERE lt.Id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return mapResultSet(rs);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error finding laboratory test: "
                            + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // UPDATE LABORATORY TEST
    // =========================================================

    public boolean update(LaboratoryTest test) {

        String sql = """
                UPDATE LaboratoryTest
                SET
                    PatientId = ?,
                    TechnicianId = ?,
                    TestName = ?,
                    TestDate = ?,
                    Result = ?,
                    ReferenceRange = ?,
                    Status = ?
                WHERE Id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Patient
            ps.setInt(
                    1,
                    test.getPatient().getPatientId()
            );

            // Technician
            if (test.getTechnician() != null) {

                ps.setInt(
                        2,
                        test.getTechnician().getStaffId()
                );

            } else {

                ps.setNull(
                        2,
                        Types.INTEGER
                );
            }

            // Test name
            ps.setString(
                    3,
                    test.getTestName()
            );

            // Test date
            if (test.getTestDate() != null) {

                ps.setTimestamp(
                        4,
                        Timestamp.valueOf(
                                test.getTestDate()
                        )
                );

            } else {

                ps.setTimestamp(
                        4,
                        Timestamp.valueOf(
                                LocalDateTime.now()
                        )
                );
            }

            // Result
            ps.setString(
                    5,
                    test.getResult()
            );

            // Reference range
            ps.setString(
                    6,
                    test.getReferenceRange()
            );

            // Status
            ps.setString(
                    7,
                    test.getStatus()
            );

            // ID
            ps.setInt(
                    8,
                    test.getId()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating laboratory test: "
                            + e.getMessage()
            );
        }

        return false;
    }


    // =========================================================
    // UPDATE LABORATORY RESULT
    // =========================================================

    public boolean updateResult(
            int testId,
            String result,
            String status) {

        String sql = """
                UPDATE LaboratoryTest
                SET
                    Result = ?,
                    Status = ?
                WHERE Id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(
                    1,
                    result
            );

            ps.setString(
                    2,
                    status
            );

            ps.setInt(
                    3,
                    testId
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating laboratory result: "
                            + e.getMessage()
            );
        }

        return false;
    }


    // =========================================================
    // DELETE LABORATORY TEST
    // =========================================================

    public boolean delete(int id) {

        String sql = """
                DELETE FROM LaboratoryTest
                WHERE Id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting laboratory test: "
                            + e.getMessage()
            );
        }

        return false;
    }


    // =========================================================
    // MAP RESULT SET TO LABORATORY TEST
    // =========================================================

    private LaboratoryTest mapResultSet(ResultSet rs)
            throws SQLException {

        LaboratoryTest test = new LaboratoryTest();

        test.setId(
                rs.getInt("Id")
        );

        /*
         * Patient and Technician objects are not created here.
         * Only their IDs are available from this query.
         *
         * If your LaboratoryTest model requires complete
         * Patient and LaboratoryTechnician objects, we can
         * modify this DAO to JOIN those tables.
         */

        test.setTestName(
                rs.getString("TestName")
        );

        Timestamp testDate =
                rs.getTimestamp("TestDate");

        if (testDate != null) {

            test.setTestDate(
                    testDate.toLocalDateTime()
            );
        }

        test.setResult(
                rs.getString("Result")
        );

        test.setReferenceRange(
                rs.getString("ReferenceRange")
        );

        test.setStatus(
                rs.getString("Status")
        );

        return test;
    }
}