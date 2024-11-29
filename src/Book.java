public class Book {
    private int id;
    private String title;
    private boolean available;
    private Member borrower; // track the borrower

    public Book(int id, String title) throws Exception {
        if (!isValidId(id)) {
            throw new Exception("Invalid Book ID: ID must be between 100 and 999.");
        }
        this.id = id;
        this.title = title;
        this.available = true;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    // Getter and Setter for borrower
    public Member getBorrower() {
        return borrower;
    }

    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }

    // Method to borrow the book
    public void borrowBook() {
        if (available) {
            available = false; //book availability
        }
    }

    // Method to return the book
    public void returnBook() {
        if (!available) {
            available = true; // book availability
        }
    }


    // Method book id check
    public boolean isValidId(int id) {
        return id >= 100 && id <= 999;
    }
}
