package mustache.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mustache.practice.domain.entity.Article;
import mustache.practice.domain.entity.Comment;

import java.util.List;

@Getter
@AllArgsConstructor
public class ArticleDto {

    private Long id;
    private String title;
    private String content;

    private List<Comment> comments;


    @Override
    public String toString() {
        return "ArticleDto{" +
                "title='" + title + '\'' +
                ", contents='" + content + '\'' +
                '}';
    }

    public Article toEntity(){
        return new Article(this.id,this.title,this.content,this.comments);
    }
}