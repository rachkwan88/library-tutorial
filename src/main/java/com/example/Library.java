package com.example;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            File myFile = new File("books.csv");
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] tokens = line.split(",");
                int bookId = Integer.parseInt(tokens[0]);
                String title = tokens[1];
                String author = tokens[2];
                Book book = new Book(bookId, title, author);
                books.add(book);
            }
            reader.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return books;
    }

    public ArrayList<BorrowedBook> getBorrowedBooks() {
        ArrayList<BorrowedBook> books = new ArrayList<BorrowedBook>();
        try {
            File myFile = new File("borrowed_books.csv");
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] tokens = line.split(",");
                int memberId = Integer.parseInt(tokens[0]);
                int bookId = Integer.parseInt(tokens[1]);
                String title = tokens[2];
                String author = tokens[3];
                BorrowedBook book = new BorrowedBook(memberId, bookId, title, author);
                books.add(book);
            }
            reader.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return books;
    }

    public void saveBooksToFile(ArrayList<Book> items) {
        try {
            FileWriter fWriter = new FileWriter("books.csv");
            for (int i = 0; i < items.size(); i++) {
                Book book = items.get(i);
                fWriter.write(book.bookId + ",");
                fWriter.write(book.title + ",");
                fWriter.write(book.author + "\n");
            }
            fWriter.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void saveBorrowedBooksToFile(ArrayList<BorrowedBook> items) {
        try {
            FileWriter fWriter = new FileWriter("borrowed_books.csv");
            for (int i = 0; i < items.size(); i++) {
                BorrowedBook book = items.get(i);
                fWriter.write(book.memberId + ",");
                fWriter.write(book.bookId + ",");
                fWriter.write(book.title + ",");
                fWriter.write(book.author + "\n");
            }
            fWriter.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void returnBook(int memberid, int bookid) throws Exception {
        ArrayList<Book> books = this.getAvailableBooks();
        ArrayList<BorrowedBook> borrowedBooks = this.getBorrowedBooks();
        for (int i = 0; i < borrowedBooks.size(); i++) {
            BorrowedBook borrowedBook = borrowedBooks.get(i);
            if (borrowedBook.bookId == bookid && borrowedBook.memberId == memberid) {
                Book book = new Book(borrowedBook.bookId, borrowedBook.title, borrowedBook.author);
                books.add(book);
                borrowedBooks.remove(i);
            }
        }

        this.saveBooksToFile(books);
        this.saveBorrowedBooksToFile(borrowedBooks);

    }

    public void borrowBook(int memberid, int bookid) throws Exception {
        ArrayList<Book> books = this.getAvailableBooks();
        ArrayList<BorrowedBook> borrowedBooks = this.getBorrowedBooks();
        // remove itemId th element
        int counter = 0;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.bookId == bookid) {
                BorrowedBook borrowed = new BorrowedBook(memberid, bookid, book.title, book.author);
                borrowedBooks.add(borrowed);
                books.remove(i);
                counter += 1;
            }
        }

        if (counter == 0) {
            throw new Exception("Bookid " + bookid + " not available for borrowing");
        }

        // save arraylist to file
        this.saveBooksToFile(books);
        this.saveBorrowedBooksToFile(borrowedBooks);

    }
}
