package mustache.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mustache.practice.domain.entity.Article;

import java.util.List;

@Getter
@AllArgsConstructor
public class ArticleDto {

    private Long id;
    private String title;
    private String content;



    @Override
    public String toString() {
        return "ArticleDto{" +
                "title='" + title + '\'' +
                ", contents='" + content + '\'' +
                '}';
    }

    public Article toEntity(){
        Article article = Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
        return article;
    }
}