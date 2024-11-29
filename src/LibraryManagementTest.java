import org.junit.Test;
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
    public void testBorrowReturn() throws Exception {
        Book book = new Book(100, "Programming");
        Member member = new Member(1111, "George");
        Transaction transaction = Transaction.getInstance();

        // Borrow book
        transaction.borrowBook(book, member);
        assertFalse(book.isAvailable()); // Book should be unavailable

        // Try borrowing again (should fail)
        try {
            transaction.borrowBook(book, member);
            fail("Exception was expected for borrowing an unavailable book.");
        } catch (Exception e) {
            assertEquals("Book is not available for borrowing.", e.getMessage());
        }

        // Return book
        transaction.returnBook(book, member);
        assertTrue(book.isAvailable()); // Book should be available again

        // Try returning again (should fail)
        try {
            transaction.returnBook(book, member);
            fail("Exception was expected for returning a book not borrowed.");
        } catch (Exception e) {
            assertEquals("Book was not borrowed by the member.", e.getMessage());
        }
    }
}
