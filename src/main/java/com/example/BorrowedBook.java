package com.example;

public class BorrowedBook {

    public int memberId = 0;
    public int bookId = 0;
    public String title = "";
    public String author = "";

    public BorrowedBook(int memberId, int bookId, String title, String author) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

}