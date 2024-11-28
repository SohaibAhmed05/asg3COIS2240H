import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        Transaction transaction = Transaction.getInstance(); // Use Singleton instance

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show Borrowed Books");
            System.out.println("6. View Transaction History");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1: // Add Member
                    System.out.print("Enter Member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Member Name: ");
                    String memberName = scanner.nextLine();
                    if (!library.addMember(new Member(memberId, memberName))) {
                        System.out.println("Failed to add member. Duplicate ID.");
                    }
                    break;

                case 2: // Add Book
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Book Title: ");
                    String bookTitle = scanner.nextLine();
                    if (!library.addBook(new Book(bookId, bookTitle))) {
                        System.out.println("Failed to add book. Duplicate ID.");
                    }
                    break;

                case 3: // Borrow Book
                    System.out.print("Enter Member ID: ");
                    int borrowerId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    int borrowedBookId = scanner.nextInt();
                    Member borrower = library.findMemberById(borrowerId);
                    Book borrowedBook = library.findBookById(borrowedBookId);

                    if (borrower != null && borrowedBook != null) {
                        transaction.borrowBook(borrowedBook, borrower);
                    } else {
                        System.out.println("Invalid Member or Book ID.");
                    }
                    break;

                case 4: // Return Book
                    System.out.print("Enter Member ID: ");
                    int returnerId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    int returnedBookId = scanner.nextInt();
                    Member returner = library.findMemberById(returnerId);
                    Book returnedBook = library.findBookById(returnedBookId);

                    if (returner != null && returnedBook != null) {
                        transaction.returnBook(returnedBook, returner);
                    } else {
                        System.out.println("Invalid Member or Book ID.");
                    }
                    break;

                case 6: // View Transaction History
                    transaction.displayTransactionHistory();
                    break;

                case 0: // Exit
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        } while (option != 0);

        scanner.close();
    }
}
