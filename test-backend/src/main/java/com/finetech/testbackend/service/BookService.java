package com.finetech.testbackend.service;

import com.finetech.testbackend.dto.BookDTO;
import com.finetech.testbackend.model.Book;

import java.util.List;

public interface BookService {

    public BookDTO addBook(BookDTO bookDTO) throws Exception;
    public boolean deleteBook(int id) throws Exception;
    public BookDTO updateBook(BookDTO bookDTO) throws Exception;
    public List<BookDTO> getAll(int offset,int limit)throws Exception;
    public BookDTO searchByName(String name) throws Exception;
    public List<BookDTO> findByCategory(int id) throws Exception;

    public BookDTO findById(Integer id) throws Exception;
}
