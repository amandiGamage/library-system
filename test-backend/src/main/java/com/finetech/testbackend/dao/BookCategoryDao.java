package com.finetech.testbackend.dao;

import com.finetech.testbackend.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryDao extends JpaRepository<BookCategory, Integer> {
}
