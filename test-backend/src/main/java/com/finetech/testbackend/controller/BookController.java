package com.finetech.testbackend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finetech.testbackend.dto.BookDTO;
import com.finetech.testbackend.model.Book;
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
public class BookController {

    @Autowired
    private BookService bookService;

    private ObjectMapper ob;

    @PostConstruct
    private void init(){
        ob = new ObjectMapper();
    }

    @PostMapping(value = "/books",consumes = "application/json")
    public ResponseEntity<JsonNode> addBook(@RequestBody String payload){

        try {
            JsonNode jsonNode = ob.readTree(payload);
            BookDTO bookDTO = ob.convertValue(jsonNode, BookDTO.class);

            BookDTO savedBook = bookService.addBook(bookDTO);

            if ( null== savedBook){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_NOT_ADDED,null)));
            }else {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.SUCCESSFUL,savedBook)));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(e.getMessage(),null)));
        }
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<JsonNode> deleteBook(@PathVariable("id")Integer id){

        try {

            boolean result = bookService.deleteBook(id);

            if (result){
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_DELETED,result)));
            }else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_NOT_DELETED,null)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(e.getMessage(),null)));
        }
    }

    @PutMapping(value = "/books/{id}")
    public ResponseEntity<JsonNode> updateBook(@PathVariable("id")Integer id,@RequestBody String payload){
        try {
            BookDTO bookDTO = bookService.findById(id);

            if (null!= bookDTO){
                JsonNode jsonNode = ob.readTree(payload);
                BookDTO convertedValue = ob.convertValue(jsonNode, BookDTO.class);
                convertedValue.setId(id);
                BookDTO updatedBookDTO = bookService.updateBook(convertedValue);

                return ResponseEntity.status(HttpStatus.OK).body(JsonService.toJsonNode(
                        new ResponseWrapper<>(ResponseMassage.BOOK_UPDATED,updatedBookDTO)
                ));
            }else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(ResponseMassage.BOOK_NOT_FOUND,null)
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

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<JsonNode> findByBookID(@PathVariable("id")Integer id){
        try {
            BookDTO searchedBookDTO = bookService.findById(id);

            if (null != searchedBookDTO){
                return ResponseEntity.status(HttpStatus.OK).body(JsonService.toJsonNode(
                        new ResponseWrapper<>(ResponseMassage.BOOK_AVAILABLE,searchedBookDTO)
                ));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(JsonService.toJsonNode(
                        new ResponseWrapper<>(ResponseMassage.BOOK_NOT_FOUND,null)
                ));
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(e.getMessage(),null)));
        }
    }

    @GetMapping(value = "/books")
    public ResponseEntity<JsonNode> getAllBooks(@RequestParam("offset") Integer offset,
                                                @RequestParam("limit")Integer limit){
        try {
            List<BookDTO> allBooks = bookService.getAll(offset,limit);

            if (allBooks.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.BOOK_NOT_FOUND,null)));
            }else {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.SUCCESSFUL,allBooks)));
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
