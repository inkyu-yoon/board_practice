package mustache.practice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mustache.practice.domain.entity.Article;
import mustache.practice.domain.entity.Hospital;
import mustache.practice.domain.entity.Review;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String title;
    private String content;
    private String patientName;
    private Hospital hospital;

    public Review toEntity(){
        Review review = Review.builder()
                .title(this.title)
                .content(this.content)
                .patientName(this.patientName)
                .hospital(this.hospital)
                .build();
        return review;
    }

}
