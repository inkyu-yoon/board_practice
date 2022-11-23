package mustache.practice.domain.entity;


import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}
