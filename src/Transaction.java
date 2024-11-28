import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Transaction {
    private static Transaction instance;

    // Private constructor for Singleton
    private Transaction() {}

    // Singleton method to get the single instance
    public static Transaction getInstance() {
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
    }

    // Save transaction details to a file
    public void saveTransaction(String transactionDetails) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true))) {
            writer.write(transactionDetails);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Borrow a book and save the transaction
    public void borrowBook(Book book, Member member) {
        if (book != null && member != null) {
            saveTransaction("Borrowed: " + book.getTitle() + " by " + member.getName());
            System.out.println("Transaction recorded: Borrowed " + book.getTitle());
        } else {
            System.out.println("Invalid book or member for borrowing.");
        }
    }

    // Return a book and save the transaction
    public void returnBook(Book book, Member member) {
        if (book != null && member != null) {
            saveTransaction("Returned: " + book.getTitle() + " by " + member.getName());
            System.out.println("Transaction recorded: Returned " + book.getTitle());
        } else {
            System.out.println("Invalid book or member for returning.");
        }
    }

    public void displayTransactionHistory() {
    }
}
