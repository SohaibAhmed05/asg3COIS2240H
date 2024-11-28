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

    // Placeholder method for borrowing a book
    public void borrowBook(Book book, Member member) {
        System.out.println("Book borrowed: " + book.getTitle() + " by " + member.getName());
    }

    // Placeholder method for returning a book
    public void returnBook(Book book, Member member) {
        System.out.println("Book returned: " + book.getTitle() + " by " + member.getName());
    }

    public void displayTransactionHistory() {
    }
}
