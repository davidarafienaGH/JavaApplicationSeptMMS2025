public class PayableInterfaceTest {
    public static void main(String[] args) {
        Payable[] payableObjects = new Payable[3];

        payableObjects[0] = new Invoice("01234", "Seat Belt Bracket", 2, 37.50);
        payableObjects[1] = new SalariedEmployee("Alex", "Turner", "333-33-3333", new Date(5, 12, 1985), 800.00);
        payableObjects[2] = new PieceWorker("Maria", "Garcia", "444-44-4444", new Date(8, 22, 1993), 2.50, 400);

        System.out.println("Processing Payable Objects Polymorphically:\n");

        for (Payable payableObject : payableObjects) {
            System.out.println(payableObject.toString());
            System.out.printf("Payment Due: $%,.2f%n%n", payableObject.getPaymentAmount());
        }
    }
}
