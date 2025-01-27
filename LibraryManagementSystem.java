import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String isbn, title, author;
    boolean isBorrowed;

    // Constructor
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    // Book details in string format
    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Title: " + title + ", Author: " + author + ", Borrowed: " + (isBorrowed ? "Yes" : "No");
    }

    // To save Book object into a string to store in the file
    public String toFileString() {
        return isbn + "," + title + "," + author + "," + isBorrowed;
    }
}

public class LibraryManagementSystem {

    static ArrayList<Book> library = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadBooksFromFile(); // Load books from file at the start

        int option;
        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Search Book");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    searchBook();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        } while (option != 7);

        saveBooksToFile(); // Save books to file when exiting
    }

    // 1. Add Book
    static void addBook() {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        library.add(new Book(isbn, title, author));
        System.out.println("Book added successfully!");
    }

    // 2. Update Book
    static void updateBook() {
        System.out.print("Enter ISBN of the book to update: ");
        String isbn = scanner.nextLine();
        for (Book book : library) {
            if (book.isbn.equals(isbn)) {
                System.out.print("Enter new Title: ");
                book.title = scanner.nextLine();
                System.out.print("Enter new Author: ");
                book.author = scanner.nextLine();
                System.out.println("Book updated successfully!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // 3. Delete Book
    static void deleteBook() {
        System.out.print("Enter ISBN of the book to delete: ");
        String isbn = scanner.nextLine();
        for (Book book : library) {
            if (book.isbn.equals(isbn)) {
                library.remove(book);
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // 4. Search Book
    static void searchBook() {
        System.out.print("Enter Title, Author, or ISBN to search: ");
        String query = scanner.nextLine();
        for (Book book : library) {
            if (book.title.equalsIgnoreCase(query) || book.author.equalsIgnoreCase(query) || book.isbn.equals(query)) {
                System.out.println("Book Found: " + book);
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // 5. Borrow Book
    static void borrowBook() {
        System.out.print("Enter ISBN of the book to borrow: ");
        String isbn = scanner.nextLine();
        for (Book book : library) {
            if (book.isbn.equals(isbn)) {
                if (!book.isBorrowed) {
                    book.isBorrowed = true;
                    System.out.println("Book borrowed successfully!");
                } else {
                    System.out.println("Book is already borrowed!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // 6. Return Book
    static void returnBook() {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine();
        for (Book book : library) {
            if (book.isbn.equals(isbn)) {
                if (book.isBorrowed) {
                    book.isBorrowed = false;
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("This book was not borrowed!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // Save books to a file
    static void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"))) {
            for (Book book : library) {
                writer.write(book.toFileString());
                writer.newLine();
            }
            System.out.println("Books saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    // Load books from a file
    static void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String isbn = parts[0];
                String title = parts[1];
                String author = parts[2];
                boolean isBorrowed = Boolean.parseBoolean(parts[3]);

                library.add(new Book(isbn, title, author));
                library.get(library.size() - 1).isBorrowed = isBorrowed;
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }
}
