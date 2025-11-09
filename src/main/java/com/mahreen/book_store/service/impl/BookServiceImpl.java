package com.mahreen.book_store.service.impl;

import com.mahreen.book_store.dto.BookDto;
import com.mahreen.book_store.entity.Book;
import com.mahreen.book_store.exception.BookNotFoundException;
import com.mahreen.book_store.mapper.BookMapper;
import com.mahreen.book_store.repository.BookRepository;
import com.mahreen.book_store.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto getBook(String bookId) {
        //Optional<T> is a container object introduced in Java 8.
        //It’s used to represent a value that may or may not be present — instead of returning null.
        Optional<Book> optionalBook = Optional.ofNullable(bookRepository.findBookById(bookId));

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return BookMapper.toDto(book);
        }

        else {
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found.");
        }

        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : books) {
            bookDtoList.add(BookMapper.toDto(book));
        }
        return bookDtoList;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = bookRepository.insert(BookMapper.toEntity(bookDto));
        return BookMapper.toDto(book);
    }

    @Override
    public BookDto updateBookName(BookDto bookDto) {
        Optional<Book> book = Optional.ofNullable(bookRepository.findBookById(bookDto.bookId()));

        if(book.isPresent()) {
            bookRepository.updateBookNameByBookId(bookDto.bookId(), bookDto.name());
            return this.getBook(bookDto.bookId());
        }
        else {
            throw new BookNotFoundException("Book not found with ID: " + bookDto.bookId());
        }
    }

    @Override
    public void deleteBookByBookId(String bookId) {

        Optional<Book> book = Optional.ofNullable(bookRepository.findBookById(bookId));
        if(book.isPresent()) {
            bookRepository.deleteBookByBookId(bookId);
        }

        else {
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }

    }
}
