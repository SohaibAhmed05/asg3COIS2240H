import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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

    public void borrowBook(Book book, Member member) {
        if (book.isAvailable()) { // Check if the book is available
            book.setBorrower(member); // Assign the borrower
            book.borrowBook(); // Set the book as unavailable
            saveTransaction("Borrowed: " + book.getTitle() + " by " + member.getName());
            System.out.println("Transaction recorded: Borrowed " + book.getTitle());
        } else {
            System.out.println("Book is already borrowed by: " + book.getBorrower().getName());
            throw new IllegalStateException("Book is not available for borrowing."); // Throw an exception for test
        }
    }

    public void returnBook(Book book, Member member) {
        if (book.getBorrower() != null && book.getBorrower().equals(member)) { // Ensure the correct member is returning
            book.setBorrower(null); // Clear the borrower
            book.returnBook(); // Set the book as available
            saveTransaction("Returned: " + book.getTitle() + " by " + member.getName());
            System.out.println("Transaction recorded: Returned " + book.getTitle());
        } else {
            System.out.println("Invalid return attempt. This book was not borrowed by " + member.getName());
            throw new IllegalStateException("Book was not borrowed by the member."); // Throw an exception for test
        }
    }

    // Display transaction history from the file
    public void displayTransactionHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))) {
            System.out.println("Transaction History:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No transaction history available or error reading file.");
        }
    }
}
