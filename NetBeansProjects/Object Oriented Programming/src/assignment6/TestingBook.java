
package assignment6;

public class TestingBook {
    public static void main(String[] args) {
        Book defaultBook = new Book();
        Book customBook = new Book("To Kill a Mockingbird", "Harper Lee", 14.99);

        System.out.println("--- Default Book ---");
        defaultBook.displayDetails();

        System.out.println("\n--- Custom Book ---");
        customBook.displayDetails();
    }
}
