import java.util.Scanner;

public class BooksArray {
    public static void main(String[] args) {
        String[] books = {
            "A Call To Arms",
            "Dune",
            "Ressurection",
            "Of Mice And Men",
            "The Shining",
            "Project Hail Mary",
			"1984",
            "Born a Crime",
            "Civil War",
            "Educated"
        };

        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to the Books Array");

        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View all books");
            System.out.println("2. Search for a book");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            
            while (!scan.hasNextInt()) {
                System.out.println("That's not a valid number. Please try again.");
                scan.next();
            }
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    viewAllBooks(books);
                    break;
                case 2:
                    searchBook(books, scan);
                    break;
                case 3:
                    System.out.println("Exiting the program");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        } while (choice != 3);
    }

    public static void viewAllBooks(String[] array) {
        System.out.println("\n--- List of All Books ---");
        for (String book : array) {
            System.out.printf("%s%n",book);
        }
    }

    public static void searchBook(String[] array, Scanner scan) {
        System.out.print("\nEnter the title (or part of the title) to search for: ");
        String searchInput = scan.nextLine().trim();

        if (searchInput.isEmpty()) {
            System.out.println("Search query cannot be empty.");
            return;
        }

        boolean found = false;
        System.out.println("\n--- Search Results ---");
        
        for (String book : array) {
            // Converts both to lowercase for a case-insensitive, forgiving search
            if (book.toLowerCase().contains(searchInput.toLowerCase())) {
                System.out.println("- " + book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found matching \"" + searchInput + "\".");
        }
    }
}