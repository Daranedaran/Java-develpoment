import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    String username;
    String password;
    List<Book> borrowedBooks;

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.borrowedBooks = new ArrayList<>();
    }
}

class Book {
    String title;
    String author;
    String ISBN;
    boolean isBorrowed;

    Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isBorrowed = false;
    }
}

public class DigitalLibraryManagement {
    static List<User> users = new ArrayList<>();
    static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example books
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "1234567890"));
        books.add(new Book("1984", "George Orwell", "0987654321"));

        System.out.println("Welcome to the Digital Library Management System");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User currentUser = new User(username, password);
        users.add(currentUser);

        System.out.println("Login successful!");

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Search Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Borrowed Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title, author, or ISBN to search: ");
                    String searchQuery = scanner.nextLine();
                    searchBooks(searchQuery);
                    break;
                case 2:
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String borrowISBN = scanner.nextLine();
                    borrowBook(currentUser, borrowISBN);
                    break;
                case 3:
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnISBN = scanner.nextLine();
                    returnBook(currentUser, returnISBN);
                    break;
                case 4:
                    viewBorrowedBooks(currentUser);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using the Digital Library Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static void searchBooks(String query) {
        for (Book book : books) {
            if (book.title.contains(query) || book.author.contains(query) || book.ISBN.contains(query)) {
                System.out.println("Title: " + book.title + ", Author: " + book.author + ", ISBN: " + book.ISBN + ", Borrowed: " + book.isBorrowed);
            }
        }
    }

    static void borrowBook(User user, String ISBN) {
        for (Book book : books) {
            if (book.ISBN.equals(ISBN) && !book.isBorrowed) {
                book.isBorrowed = true;
                user.borrowedBooks.add(book);
                System.out.println("You have borrowed: " + book.title);
                return;
            }
        }
        System.out.println("Book not available.");
    }

    static void returnBook(User user, String ISBN) {
        for (Book book : user.borrowedBooks) {
            if (book.ISBN.equals(ISBN)) {
                book.isBorrowed = false;
                user.borrowedBooks.remove(book);
                System.out.println("You have returned: " + book.title);
                return;
            }
        }
        System.out.println("You have not borrowed this book.");
    }

    static void viewBorrowedBooks(User user) {
        System.out.println("Borrowed Books:");
        for (Book book : user.borrowedBooks) {
            System.out.println("Title: " + book.title + ", Author: " + book.author + ", ISBN: " + book.ISBN);
        }
    }
}