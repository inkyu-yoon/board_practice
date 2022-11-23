package mustache.practice.domain.entity;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
