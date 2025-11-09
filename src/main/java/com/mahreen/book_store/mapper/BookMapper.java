package com.mahreen.book_store.mapper;

import com.mahreen.book_store.dto.BookDto;
import com.mahreen.book_store.entity.Book;

//helping convert dto to entity and vice versa
public class BookMapper {

    public static BookDto toDto(Book book) {
        return new BookDto (book.bookId(),  book.name(), book.price(), book.author(), book.description());

    }

    public static Book toEntity(BookDto bookDto) {
        return new Book(bookDto.bookId(), bookDto.name(), bookDto.price(), bookDto.author(), bookDto.description());
    }
}
