package hospital.services;

import hospital.dao.InvoiceItemDAO;
import hospital.models.Invoice;
import hospital.models.InvoiceItem;

import java.sql.SQLException;
import java.util.List;

public class InvoiceItemService {

    private final InvoiceItemDAO invoiceItemDAO;

    public InvoiceItemService(InvoiceItemDAO invoiceItemDAO) {
        this.invoiceItemDAO = invoiceItemDAO;
    }

    public InvoiceItem addInvoiceItem(Invoice invoice, String description, int quantity, double unitPrice) throws Exception {
        if (invoice == null || invoice.getId() <= 0) {
            throw new IllegalArgumentException("A valid invoice must be attached.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Item description cannot be empty.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative.");
        }

        InvoiceItem item = new InvoiceItem();
        item.setInvoice(invoice);
        item.setDescription(description.trim());
        item.setQuantity(quantity);
        item.setUnitPrice(unitPrice); // Automatic calculateAmount() call internal to model setter

        return invoiceItemDAO.create(item);
    }

    public List<InvoiceItem> getItemsByInvoiceId(int invoiceId) throws SQLException {
        if (invoiceId <= 0) {
            throw new IllegalArgumentException("Invalid invoice ID.");
        }
        return invoiceItemDAO.findByInvoiceId(invoiceId);
    }

    public InvoiceItem getItemById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid line item ID.");
        }
        return invoiceItemDAO.findById(id);
    }

    public boolean removeInvoiceItem(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid line item ID.");
        }
        return invoiceItemDAO.delete(id);
    }
}