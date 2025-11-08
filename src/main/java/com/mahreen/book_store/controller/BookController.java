package com.mahreen.book_store.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book-store")
public class BookController {

    @GetMapping("/{bookId}")
    public ResponseEntity<String> getBook(@PathVariable String bookId){
        return new ResponseEntity<>("Book Found " + bookId, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> getAllBooks(){
        List<String> books = new ArrayList<>();
        books.add("Book 1");
        books.add("Book 2");
        books.add("Book 3");

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> createBook(String book){
        return new ResponseEntity<>("Book created", HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateBook(String book){
        return new ResponseEntity<>("Book updated", HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId){
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }


}
