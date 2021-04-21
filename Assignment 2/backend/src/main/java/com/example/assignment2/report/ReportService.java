package com.example.assignment2.report;

import com.example.assignment2.book.dto.BookDTO;

import java.io.IOException;
import java.util.List;

public interface ReportService {
    String export(List<BookDTO> noOfBooks) throws IOException;

    ReportType getType();
}
