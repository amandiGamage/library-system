package com.finetech.testbackend.model;

import javax.persistence.*;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL) /*me liyala thinneeee ithin ai ara peththeth mekama liyala thinne eka peththaka liuwama athine unidirectional
    liuwama athi neda? bydirectional liynna hethuwak thiye nm witharak*/
    @JoinColumn(name = "book_category_id")//methana danna one join column eka hedenna ona nama haaaaa
    private BookCategory bookCategory;

    public Book() {
    }

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

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
}
