import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

public class LibraryManagementTest {

    @Test
    public void testBookId() throws Exception {
        // Valid
        Book validBook1 = new Book(100, "Programming");
        Book validBook2 = new Book(999, "AI");
        assertNotNull(validBook1);
        assertNotNull(validBook2);

        // Invalid
        try {
            new Book(99, "Invalid Book");
            fail("Exception was expected for ID 99.");
        } catch (Exception e) {
            assertEquals("Invalid Book ID: ID must be between 100 and 999.", e.getMessage());
        }

        try {
            new Book(1000, "Invalid Book");
            fail("Exception was expected for ID 1000.");
        } catch (Exception e) {
            assertEquals("Invalid Book ID: ID must be between 100 and 999.", e.getMessage());
        }
    }
    @Test
    public void testSingletonTransaction() throws Exception {
        Transaction instance1 = Transaction.getInstance();
        Transaction instance2 = Transaction.getInstance();
        assertSame(instance1, instance2);


        Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }
    @Test
    public void testBorrowReturn() throws Exception {
        // Create a book and a member
        Book book = new Book(100, "Programming");
        Member member = new Member(1111, "George");

        // Get Transaction Singleton instance
        Transaction transaction = Transaction.getInstance();

        // Borrow the book
        transaction.borrowBook(book, member);
        assertFalse(book.isAvailable()); // Book should now be unavailable

        // Try borrowing again (should fail)
        try {
            transaction.borrowBook(book, member);
            fail("Exception was expected for borrowing an unavailable book.");
        } catch (IllegalStateException e) {
            assertEquals("Book is not available for borrowing.", e.getMessage());
        }

        // Return the book
        transaction.returnBook(book, member);
        assertTrue(book.isAvailable()); // Book should now be available

        // Try returning again (should fail)
        try {
            transaction.returnBook(book, member);
            fail("Exception was expected for returning a book not borrowed.");
        } catch (IllegalStateException e) {
            assertEquals("Book was not borrowed by the member.", e.getMessage());
        }
    }
}