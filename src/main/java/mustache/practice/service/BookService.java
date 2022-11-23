package mustache.practice.service;

import lombok.RequiredArgsConstructor;
import mustache.practice.domain.dto.ArticleResponse;
import mustache.practice.domain.dto.BookResponseDto;
import mustache.practice.domain.entity.Article;
import mustache.practice.domain.entity.Author;
import mustache.practice.domain.entity.Book;
import mustache.practice.domain.entity.Publisher;
import mustache.practice.repository.AuthorRepository;
import mustache.practice.repository.BookRepository;
import mustache.practice.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public List<BookResponseDto> findBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        List<BookResponseDto> bookResponseDtos = books.stream()
                .map(book -> {
                    Optional<Author> optionalAuthor = authorRepository.findById(book.getAuthor().getId());
                    Optional<Publisher> optionalPublisher = publisherRepository.findById(book.getPublisher().getId());
                    return BookResponseDto.of(book, optionalAuthor.get().getName(),optionalPublisher.get().getName());
                }).collect(Collectors.toList());
        return bookResponseDtos;
    }


}
