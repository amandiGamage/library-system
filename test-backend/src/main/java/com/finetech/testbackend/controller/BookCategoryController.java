package com.finetech.testbackend.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finetech.testbackend.dto.BookCategoryDTO;
import com.finetech.testbackend.dto.BookDTO;
import com.finetech.testbackend.model.BookCategory;
import com.finetech.testbackend.service.BookCategoryService;
import com.finetech.testbackend.service.BookService;
import com.finetech.testbackend.util.JsonService;
import com.finetech.testbackend.util.ResponseMassage;
import com.finetech.testbackend.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class BookCategoryController {

    @Autowired
    private BookCategoryService bookCategoryService;

    @Autowired
    private BookService bookService;

    private ObjectMapper objectMapper;

    @PostConstruct
    private void init(){
        objectMapper = new ObjectMapper();
    }

    @PostMapping(value = "/books/categories")
    public ResponseEntity<JsonNode> addBookCategory(@RequestBody String payload) {
        try {
            JsonNode jsonNode = objectMapper.readTree(payload);
            BookCategoryDTO bookCategoryDTO = objectMapper.convertValue(jsonNode, BookCategoryDTO.class);
            BookCategoryDTO savedBookCategory = bookCategoryService.addBookCategory(bookCategoryDTO);

            if ( null== savedBookCategory) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_CATEGORY_NOT_ADDED, null)));
            } else {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.SUCCESSFUL, savedBookCategory)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(e.getMessage(), null)));
        }
    }

    @DeleteMapping(value = "/books/categories/{id}")
    public ResponseEntity<JsonNode> deleteBookCategory(){
        try {
            boolean result = bookCategoryService.deleteBookCategory(2);

            if (result){
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_CATEGORY_DELETED, result)));
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_CATEGORY_NOT_ADDED,null)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(e.getMessage(),null)));
        }
    }

    @GetMapping(value = "/categories/{id}/books")
    public ResponseEntity<JsonNode> findByCategory(@PathVariable("id")Integer id){
        try {
            List<BookDTO> getList = bookService.findByCategory(id);

            if (getList.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_NOT_FOUND,null)));
            }else {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_AVAILABLE,getList)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(e.getMessage(),null)));
        }

    }
}
