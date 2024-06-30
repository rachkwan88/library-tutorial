package com.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void testMode() {
        Library library = new Library();

        // before borrowing
        ArrayList<Book> books = library.getAvailableBooks();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println(book.bookId + " " + book.title + " " + book.author);
        }

        // borrowing
        try {
            library.borrowBook(1, 1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // after borrowing
        books = library.getAvailableBooks();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println(book.bookId + " " + book.title + " " + book.author);
        }

        // returning
        try {
            library.returnBook(1, 1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // after returning
        books = library.getAvailableBooks();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println(book.bookId + " " + book.title + " " + book.author);
        }
    }

    public static void interactiveMode() {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int memberId = 1;

        while (true) {
            System.out.println("Enter in command (borrow/return/exit/search/list): ");
            String userInput = scanner.nextLine();

            if (userInput.equals("borrow")) {
                System.out.println("Enter in bookid: ");
                int bookid = scanner.nextInt();
                try {
                    library.borrowBook(memberId, bookid);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            if (userInput.equals("return")) {
                System.out.println("Enter in bookid: ");
                int bookid = scanner.nextInt();
                try {
                    library.returnBook(memberId, bookid);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            if (userInput.equals("list")) {
                ArrayList<Book> books = library.getAvailableBooks();
                for (int i = 0; i < books.size(); i++) {
                    Book book = books.get(i);
                    System.out.println(book.bookId + " " + book.title + " " + book.author);
                }
            }

            if (userInput.equals("exit")) {
                System.exit(0);
            }

        }
    }

    public static void main(String[] args) {
        interactiveMode();
    }
}
