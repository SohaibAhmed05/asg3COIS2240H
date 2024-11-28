import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members;
    private List<Book> books;

    public Library() {
        members = new ArrayList<>();
        books = new ArrayList<>();
    }

    // Add a new member if the ID is unique
    public boolean addMember(Member member) {
        if (findMemberById(member.getId()) != null) {
            System.out.println("Member with ID " + member.getId() + " already exists.");
            return false; // Duplicate ID
        }
        members.add(member);
        System.out.println("Member added successfully: " + member.getName());
        return true; // Successfully added
    }

    // Add a new book if the ID is unique
    public boolean addBook(Book book) {
        if (findBookById(book.getId()) != null) {
            System.out.println("Book with ID " + book.getId() + " already exists.");
            return false; // Duplicate ID
        }
        books.add(book);
        System.out.println("Book added successfully: " + book.getTitle());
        return true; // Successfully added
    }

    // Find a member by ID
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null; // Not found
    }
    public void showBorrowedBooks() {
        for (Member member : members) {
            System.out.println("Member: " + member.getName());
            System.out.println("Borrowed Books:");
            boolean hasBorrowedBooks = false;
            for (Book book : books) {
                if (book.getBorrower() != null && book.getBorrower().equals(member)) {
                    System.out.println("- " + book.getTitle());
                    hasBorrowedBooks = true;
                }
            }
            if (!hasBorrowedBooks) {
                System.out.println("No books borrowed.");
            }
            System.out.println(); // Separate each member's details
        }
    }

    // Find a book by ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null; // Not found
    }
}
