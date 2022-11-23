package mustache.practice.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mustache.practice.domain.dto.ArticleResponse;
import mustache.practice.domain.dto.BookResponseDto;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    public static BookResponseDto of(Book book) {
        return new BookResponseDto(book.getId(), book.getName(), book.getPublisher().getName(), book.getAuthor().getName());
    }
}
