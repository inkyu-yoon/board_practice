package mustache.practice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mustache.practice.domain.entity.Hospital;
import mustache.practice.domain.entity.Review;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class ReviewAddRequestDto {

    private String title;
    private String content;
    private String patientName;
    private Long hospitalId;

}
