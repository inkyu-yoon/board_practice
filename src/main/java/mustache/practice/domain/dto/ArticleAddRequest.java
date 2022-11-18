package mustache.practice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import mustache.practice.domain.entity.Article;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ArticleAddRequest {
    private String title;
    private String content;

    public Article toEntity() {
        Article article = Article.builder().title(this.title)
                .content(this.content)
                .build();
        return article;
    }
}
