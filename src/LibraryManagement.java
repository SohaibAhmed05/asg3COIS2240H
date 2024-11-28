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
                    int borrowMemberId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    int borrowBookId = scanner.nextInt();
                    Member borrowMember = library.findMemberById(borrowMemberId);
                    Book borrowBook = library.findBookById(borrowBookId);
                    transaction.borrowBook(borrowBook, borrowMember);
                    break;

                case 4: // Return Book
                    System.out.print("Enter Member ID: ");
                    int returnMemberId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    int returnBookId = scanner.nextInt();
                    Member returnMember = library.findMemberById(returnMemberId);
                    Book returnBook = library.findBookById(returnBookId);
                    transaction.returnBook(returnBook, returnMember);
                    break;

                case 6: // View Transaction History
                    transaction.displayTransactionHistory();
                    break;

                case 0:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (option != 0);

        scanner.close();
    }
}
