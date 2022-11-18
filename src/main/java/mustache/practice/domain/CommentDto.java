package mustache.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mustache.practice.domain.entity.Article;
import mustache.practice.domain.entity.Comment;

@Getter
@AllArgsConstructor
public class CommentDto {

    private Long id;

    private String user;
    private String content;


    private Article article;
    public Comment toEntity(){
        return new Comment(this.id,this.user,this.content,this.article);
    }
}
