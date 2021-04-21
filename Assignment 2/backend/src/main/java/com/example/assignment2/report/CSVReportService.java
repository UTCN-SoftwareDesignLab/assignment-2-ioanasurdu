package com.example.assignment2.report;

import com.example.assignment2.book.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.example.assignment2.report.ReportType.CSV;

@Service
public class CSVReportService implements ReportService {
    @Override
    public String export(List<BookDTO> noOfBooks) {
        String fileName = "report.csv";
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            bw.write("TITLE, AUTHOR, GENRE\n");
            for(BookDTO book: noOfBooks) {
                StringBuilder sb = new StringBuilder();
                sb.append(book.getTitle());
                sb.append(",");
                sb.append(book.getAuthor());
                sb.append(",");
                sb.append(book.getGenre());
                sb.append("\n");
                bw.write(sb.toString());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
    @Override
    public ReportType getType() {
        return CSV;
    }
}
