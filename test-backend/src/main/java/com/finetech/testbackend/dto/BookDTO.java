package com.finetech.testbackend.dto;

public class BookDTO {

    private int id;
    private String name;
    private String bookCategoryID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookCategoryID() {
        return bookCategoryID;
    }

    public void setBookCategoryID(String bookCategoryID) {
        this.bookCategoryID = bookCategoryID;
    }
}
