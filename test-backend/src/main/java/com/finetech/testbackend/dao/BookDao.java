package com.finetech.testbackend.dao;

import com.finetech.testbackend.model.Book;
import com.finetech.testbackend.model.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {
    public Book findByName(String name) throws Exception;
    public List<Book> findByBookCategory(BookCategory bookCategory) throws Exception;
    public Page<Book> findAll(Pageable pageable);
}
