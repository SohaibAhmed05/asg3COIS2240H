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
}
