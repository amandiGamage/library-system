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
import java.util.Locale;

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

    //Use for creating resources.
    @CrossOrigin(origins = "http://localhost:4200")//Cross origin allow
    @PostMapping(value = "/books/categories", consumes = "application/json")
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

    //Use for creating resources.
    @CrossOrigin(origins = "http://localhost:4200")//Cross origin allow
    @DeleteMapping(value = "/books/categories/{id}")
    public ResponseEntity<JsonNode> deleteBookCategory(@PathVariable("id")Integer id){
        try {
            boolean result = bookCategoryService.deleteBookCategory(id);

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

    //Use for creating resources.
    @CrossOrigin(origins = "http://localhost:4200")//Cross origin allow
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

    //Use for creating resources.
    @CrossOrigin(origins = "http://localhost:4200")//Cross origin allow
    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<JsonNode> getCategory(@PathVariable("id")Integer id){
        try {
            BookCategoryDTO searchedCategory = bookCategoryService.findByID(id);

            if (null!= searchedCategory){
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_CATEGORY_AVAILABLE,searchedCategory)));
            }else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_CATEGORY_NOT_FOUND,null)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(e.getMessage(),null)));
        }
    }

    //Use for creating resources.
    @CrossOrigin(origins = "http://localhost:4200")//Cross origin allow
    @PutMapping(value = "/categories/{id}")
    public ResponseEntity<JsonNode> updatCategory(@PathVariable("id")Integer id,@RequestBody String payload){
        try {
            BookCategoryDTO bookCategoryDTO = bookCategoryService.findByID(id);

            if (null!= bookCategoryDTO){
                JsonNode jsonNode = objectMapper.readTree(payload);
                BookCategoryDTO convertedValue = objectMapper.convertValue(jsonNode, BookCategoryDTO.class);
                convertedValue.setId(id);
                BookCategoryDTO updatedBookCategoryDto = bookCategoryService.updateBookCategory(convertedValue);

                return ResponseEntity.status(HttpStatus.OK).body(JsonService.toJsonNode(
                        new ResponseWrapper<>(ResponseMassage.BOOK_CATEGORY_UPDATED,updatedBookCategoryDto)
                ));
            }else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_CATEGORY_NOT_UPDATED,null)
                        ));
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
