package mustache.practice.controller;

import lombok.RequiredArgsConstructor;
import mustache.practice.domain.dto.BookResponseDto;
import mustache.practice.domain.entity.Book;
import mustache.practice.repository.BookRepository;
import mustache.practice.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;


    @GetMapping("")
    public ResponseEntity<List<BookResponseDto>> list(Pageable pageable) {
        return ResponseEntity.ok().body(bookService.findBooks(pageable));
    }
}
