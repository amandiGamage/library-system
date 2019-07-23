package com.finetech.testbackend.service;

import com.finetech.testbackend.dto.BookCategoryDTO;
import com.finetech.testbackend.dto.BookDTO;

import java.util.List;

public interface BookCategoryService {

    public BookCategoryDTO addBookCategory(BookCategoryDTO bookCategoryDTO) throws Exception;
    public boolean deleteBookCategory(int id)throws Exception;
    public BookCategoryDTO updateBookCategory(BookCategoryDTO bookCategoryDTO)throws Exception;
    public List<BookCategoryDTO> getAll()throws Exception;
    public BookCategoryDTO searchByName(String name)throws Exception;
    public BookCategoryDTO findByID(int id)throws Exception;
}
