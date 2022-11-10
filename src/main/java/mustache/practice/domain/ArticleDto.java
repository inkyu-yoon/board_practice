package mustache.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mustache.practice.domain.entity.Article;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public class ArticleDto {

    private Long id;
    private String title;
    private String contents;



    @Override
    public String toString() {
        return "ArticleDto{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }

    public Article toEntity(){
        return new Article(this.id,this.title,this.contents);
    }
}
