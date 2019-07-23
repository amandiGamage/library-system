package com.finetech.testbackend.service.impl;

import com.finetech.testbackend.dao.BookDao;
import com.finetech.testbackend.dto.BookCategoryDTO;
import com.finetech.testbackend.dto.BookDTO;
import com.finetech.testbackend.model.Book;
import com.finetech.testbackend.model.BookCategory;
import com.finetech.testbackend.service.BookCategoryService;
import com.finetech.testbackend.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookCategoryService bookCategoryService;

    @Override
    public BookDTO addBook(BookDTO bookDTO) throws Exception {
        BookCategoryDTO bookCategoryDTO = bookCategoryService.findByID(Integer.parseInt(bookDTO.getBookCategoryID()));

        Book book = new Book();
        BeanUtils.copyProperties(bookDTO,book);

        BookCategory bookCategory = new BookCategory();
        BeanUtils.copyProperties(bookCategoryDTO,bookCategory);
        book.setBookCategory(bookCategory);
        Book savedBook = bookDao.save(book);

        if (null!= savedBook){
            bookDTO.setId(savedBook.getId());
            return bookDTO;
        }else {
            return null;
        }
    }

    @Override
    public boolean deleteBook(int id) throws Exception{
        bookDao.deleteById(id);
        return true;
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) throws Exception{
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO,book);
        Book updatedBook = bookDao.save(book);

        if ( null!= updatedBook){
            return bookDTO;
        }else {
            return null;
        }
    }

    @Override
    public List<BookDTO> getAll(int offset,int limit) throws Exception{
        Pageable pageable =PageRequest.of(offset, limit);
        Page<Book> allBooks = bookDao.findAll(pageable);

        if (allBooks.isEmpty()){
            return null;
        }else {
            List<BookDTO> bookDTOList = new ArrayList<>();
            for (Book listItem : allBooks){
                BookDTO bookDTO = new BookDTO();
                BeanUtils.copyProperties(listItem,bookDTO);
                bookDTOList.add(bookDTO);
            }
            return bookDTOList;
        }
    }

    @Override
    public BookDTO searchByName(String name) throws Exception{
        Book searchedBook = bookDao.findByName(name);

        if (null != searchedBook){
            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(searchedBook,bookDTO);
            return bookDTO;
        }else {
            return null;
        }
    }

    @Override
    public List<BookDTO> findByCategory(int id) throws Exception{
        BookCategoryDTO bookCategoryDTO = bookCategoryService.findByID(id);

        if (null != bookCategoryDTO ) {
            BookCategory bookCategory = new BookCategory();
            BeanUtils.copyProperties(bookCategoryDTO, bookCategory);
            List<Book> bookList = bookDao.findByBookCategory(bookCategory);

            if (bookList.isEmpty()){
                return null;
            }else {
                List<BookDTO> bookDTOList = new ArrayList<>();
                for (Book item : bookList) {
                    BookDTO bookDTO = new BookDTO();
                    BeanUtils.copyProperties(item, bookDTO);
                    bookDTOList.add(bookDTO);
                }
                return bookDTOList;
            }
        }else {
            return null;
        }
    }

    @Override
    public BookDTO findById(Integer id) throws Exception {
        Optional<Book> searchedBooks = bookDao.findById(id);

        if (searchedBooks.isPresent()){
            Book book = searchedBooks.get();
            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(book,bookDTO);
            return bookDTO;
        }else{
            return null;
        }
    }
}
