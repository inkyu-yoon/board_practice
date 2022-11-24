package mustache.practice.domain.entity;

import lombok.*;
import mustache.practice.domain.dto.ArticleResponse;
import mustache.practice.domain.dto.ReviewAddRequestDto;
import mustache.practice.domain.dto.ReviewAddResponseDto;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String title;
    private String content;

    @Column(name = "patient_name")
    private String patientName;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public static ReviewAddRequestDto of(Review review) {
        return new ReviewAddRequestDto(review.title, review.content, review.patientName, review.getHospital().getId());
    }
}
