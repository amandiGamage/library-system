package com.finetech.testbackend.dto;

import java.util.List;

public class BookCategoryDTO {

    private int id;
    private String name;
    private List<String> bookIDs;

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

    public List<String> getBookIDs() {
        return bookIDs;
    }

    public void setBookIDs(List<String> bookIDs) {
        this.bookIDs = bookIDs;
    }
}
