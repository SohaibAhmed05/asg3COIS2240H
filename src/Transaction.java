import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Transaction {
    private static Transaction instance;

    // Private constructor for Singleton
    private Transaction() {}

    // Singleton method to get instance
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
            System.out.println("Error writing to transactions file: " + e.getMessage());
        }
    }

    // Display transaction history
    public void displayTransactionHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No transaction history available.");
        }
    }

    // Example method for borrowing a book
    public void borrowBook(Book book, Member member) {
        // Simulate borrowing logic
        saveTransaction("Borrowed: " + book.getTitle() + " by " + member.getName());
    }

    // Example method for returning a book
    public void returnBook(Book book, Member member) {
        // Simulate returning logic
        saveTransaction("Returned: " + book.getTitle() + " by " + member.getName());
    }
}
