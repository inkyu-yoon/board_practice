package mustache.practice.domain.dto;

import lombok.*;
import mustache.practice.domain.entity.Book;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private Long id;
    private String name;
    private String publisherName;
    private String authorName;


    public static BookResponseDto of(Book book) {
        return BookResponseDto.builder().id(book.getId()).name(book.getName()).authorName(book.getAuthor().getName()).publisherName(book.getPublisher().getName()).build();
    }

}
