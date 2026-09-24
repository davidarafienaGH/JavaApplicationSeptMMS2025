package hospital.dao;

import hospital.models.Invoice;
import hospital.models.InvoiceItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceItemDAO {

    private final Connection connection;

    public InvoiceItemDAO(Connection connection) {
        this.connection = connection;
    }

    public InvoiceItem create(InvoiceItem item) throws SQLException {
        String sql = "INSERT INTO InvoiceItem (invoice_id, description, quantity, unitPrice, amount) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, item.getInvoice().getId());
            stmt.setString(2, item.getDescription());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getUnitPrice());
            stmt.setDouble(5, item.getAmount());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating invoice item failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    InvoiceItem createdItem = findById(generatedKeys.getInt(1));
                    recalculateInvoiceTotal(item.getInvoice().getId());
                    return createdItem;
                } else {
                    throw new SQLException("Creating invoice item failed, no ID obtained.");
                }
            }
        }
    }

    public InvoiceItem findById(int id) throws SQLException {
        String sql = "SELECT ii.id, ii.invoice_id, ii.description, ii.quantity, ii.unitPrice, ii.amount " +
                     "FROM InvoiceItem ii WHERE ii.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToInvoiceItem(rs);
                }
            }
        }
        return null;
    }

    public List<InvoiceItem> findByInvoiceId(int invoiceId) throws SQLException {
        List<InvoiceItem> items = new ArrayList<>();
        String sql = "SELECT ii.id, ii.invoice_id, ii.description, ii.quantity, ii.unitPrice, ii.amount " +
                     "FROM InvoiceItem ii WHERE ii.invoice_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, invoiceId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    items.add(mapResultSetToInvoiceItem(rs));
                }
            }
        }
        return items;
    }

    public boolean update(InvoiceItem item) throws SQLException {
        String sql = "UPDATE InvoiceItem SET description = ?, quantity = ?, unitPrice = ?, amount = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getDescription());
            stmt.setInt(2, item.getQuantity());
            stmt.setDouble(3, item.getUnitPrice());
            stmt.setDouble(4, item.getAmount());
            stmt.setInt(5, item.getId());

            boolean updated = stmt.executeUpdate() > 0;
            if (updated) {
                recalculateInvoiceTotal(item.getInvoice().getId());
            }
            return updated;
        }
    }

    public boolean delete(int id) throws SQLException {
        InvoiceItem item = findById(id);
        if (item == null) return false;

        String sql = "DELETE FROM InvoiceItem WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            boolean deleted = stmt.executeUpdate() > 0;
            if (deleted) {
                recalculateInvoiceTotal(item.getInvoice().getId());
            }
            return deleted;
        }
    }

    private void recalculateInvoiceTotal(int invoiceId) throws SQLException {
        String sumSql = "SELECT SUM(amount) FROM InvoiceItem WHERE invoice_id = ?";
        double newTotal = 0.0;
        try (PreparedStatement stmt = connection.prepareStatement(sumSql)) {
            stmt.setInt(1, invoiceId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    newTotal = rs.getDouble(1);
                }
            }
        }

        String updateSql = "UPDATE Invoice SET totalAmount = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateSql)) {
            stmt.setDouble(1, newTotal);
            stmt.setInt(2, invoiceId);
            stmt.executeUpdate();
        }
    }

    private InvoiceItem mapResultSetToInvoiceItem(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(rs.getInt("invoice_id"));

        InvoiceItem item = new InvoiceItem();
        try {
            java.lang.reflect.Field idField = InvoiceItem.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(item, rs.getInt("id"));
        } catch (Exception e) {
            throw new SQLException("Error mapping invoice item ID", e);
        }

        item.setInvoice(invoice);
        item.setDescription(rs.getString("description"));
        item.setQuantity(rs.getInt("quantity"));
        item.setUnitPrice(rs.getDouble("unitPrice"));

        return item;
    }
}