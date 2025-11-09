package com.mahreen.book_store.repository;

import com.mahreen.book_store.entity.Book;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

//directly talks to database
public interface BookRepository extends MongoRepository<Book,String> {
    //?0 is the first parameter of the method arg
    @Query("{'bookId' : ?0}")
    Book findBookById(String bookId);

    @Query(value = "{'bookId' : ?0 }")
    @Update(pipeline = { " {'$set' : {'name' : ?1 }}"})
    void updateBookNameByBookId (String bookId, String name);

    //will fetch the id and delete it
    @DeleteQuery
    void deleteBookByBookId(String bookId);

}
