package mustache.practice.domain.entity;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String user;
    private String content;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

}
