package com.example.assignment2.book.service;

import com.example.assignment2.book.dto.BookDTO;
import com.example.assignment2.book.mapper.BookMapper;
import com.example.assignment2.book.model.Book;
import com.example.assignment2.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + id));
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO create(BookDTO book) {
        return bookMapper.toDto(bookRepository.save(
                bookMapper.fromDto(book)
        ));
    }

    public BookDTO edit(BookDTO book) {
        Book actBook = findById(book.getId());
        actBook.setTitle(book.getTitle());
        actBook.setAuthor(book.getAuthor());
        actBook.setGenre(book.getGenre());
        actBook.setQuantity(book.getQuantity());
        actBook.setPrice(book.getPrice());
        return bookMapper.toDto(
                bookRepository.save(actBook)
        );
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        bookRepository.delete(book);
    }

    public void sell(BookDTO book) {
        Book actBook = findById(book.getId());
        actBook.setQuantity(actBook.getQuantity() - 1);
        bookRepository.save(actBook);
    }

    public List<BookDTO> getNoOfBooks() {
        return bookRepository.findNoOfBooks().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}
