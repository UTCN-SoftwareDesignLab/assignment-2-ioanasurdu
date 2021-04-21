package com.example.assignment2.book.controller;

import com.example.assignment2.book.dto.BookDTO;
import com.example.assignment2.book.service.BookService;
import com.example.assignment2.report.ReportServiceFactory;
import com.example.assignment2.report.ReportType;
import com.example.assignment2.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.example.assignment2.UrlMapping.BOOKS;
import static com.example.assignment2.UrlMapping.EXPORT_REPORT;

@RestController
@RequestMapping(BOOKS)
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<BookDTO> allBooks() {
        return bookService.findAll();
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO book) {
        return bookService.create(book);
    }

    @PatchMapping
    public BookDTO edit(@RequestBody BookDTO book) {
        return bookService.edit(book);
    }

    @DeleteMapping
    public void delete(@RequestBody BookDTO book) {
        bookService.delete(book.getId());
    }

    @PatchMapping("/{id}")
    public void sell(@RequestBody BookDTO book) {
        bookService.sell(book);
    }

    @GetMapping(EXPORT_REPORT)
    public String exportReport(@PathVariable ReportType type) throws IOException {
        return reportServiceFactory.getReportService(type).export(bookService.getNoOfBooks());
    }
}
