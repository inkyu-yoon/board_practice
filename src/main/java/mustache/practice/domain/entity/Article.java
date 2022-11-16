package mustache.practice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mustache.practice.domain.dto.ArticleResponse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "article")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static ArticleResponse of(Article article) {
        return new ArticleResponse(article.getId(), article.getTitle(), article.getContent());
    }
}