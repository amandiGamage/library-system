package com.finetech.testbackend.service.impl;

import com.finetech.testbackend.dao.BookCategoryDao;
import com.finetech.testbackend.dto.BookCategoryDTO;
import com.finetech.testbackend.model.BookCategory;
import com.finetech.testbackend.service.BookCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    private BookCategoryDao bookCategoryDao;

    @Override
    public BookCategoryDTO addBookCategory(BookCategoryDTO bookCategoryDTO) throws Exception{
        BookCategory bookCategory = new BookCategory();
        BeanUtils.copyProperties(bookCategoryDTO,bookCategory);
        BookCategory addedBookCategory = bookCategoryDao.save(bookCategory);

        if (null != addedBookCategory ){
            bookCategoryDTO.setId(addedBookCategory.getId());
            return bookCategoryDTO;
        }else {
            return null;
        }
    }

    @Override
    public boolean deleteBookCategory(int id) throws Exception{
        bookCategoryDao.deleteById(id);
        return true;
    }

    @Override
    public BookCategoryDTO updateBookCategory(BookCategoryDTO bookCategoryDTO) throws Exception{
        return null;
    }

    @Override
    public List<BookCategoryDTO> getAll() throws Exception{
        return null;
    }

    @Override
    public BookCategoryDTO searchByName(String name) throws Exception{
        return null;
    }

    @Override
    public BookCategoryDTO findByID(int id) throws Exception{
        Optional<BookCategory> searchedBookCategory = bookCategoryDao.findById(id);

        if (searchedBookCategory.isPresent()){
            BookCategory bookCategory = searchedBookCategory.get();
            BookCategoryDTO bookCategoryDTO = new BookCategoryDTO();
            BeanUtils.copyProperties(bookCategory,bookCategoryDTO);
            return bookCategoryDTO;
        }else {
            return null;
        }


    }
}
