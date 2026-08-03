public class Invoice implements Payable {
    private final String partNumber;
    private final String partDescription;
    private int quantity;
    private double pricePerItem;

    public Invoice(String partNumber, String partDescription, int quantity, double pricePerItem) {
        this.partNumber = partNumber;
        this.partDescription = partDescription;
        this.quantity = Math.max(quantity, 0);
        this.pricePerItem = Math.max(pricePerItem, 0.0);
    }

    public int getQuantity() { return quantity; }
    public double getPricePerItem() { return pricePerItem; }

    @Override
    public double getPaymentAmount() {
        return getQuantity() * getPricePerItem();
    }

    @Override
    public String toString() {
        return String.format("Invoice:%nPart Number: %s (%s)%nQuantity: %d%nPrice per item: $%,.2f",
            partNumber, partDescription, getQuantity(), getPricePerItem());
    }
}
