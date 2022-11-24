package mustache.practice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mustache.practice.domain.entity.Hospital;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewAddResponseDto {


    private String title;
    private String content;
    private String patientName;
    private String message;
}
