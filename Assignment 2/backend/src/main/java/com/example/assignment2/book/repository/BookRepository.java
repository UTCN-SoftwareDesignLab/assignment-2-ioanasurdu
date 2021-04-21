package com.example.assignment2.book.repository;

import com.example.assignment2.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "Select b from Book where b.quantity = 0", nativeQuery = true)
    List<Book> findNoOfBooks();

}
