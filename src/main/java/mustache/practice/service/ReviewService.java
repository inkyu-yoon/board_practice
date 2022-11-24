package mustache.practice.service;

import lombok.RequiredArgsConstructor;
import mustache.practice.domain.dto.HospitalResponse;
import mustache.practice.domain.dto.ReviewAddRequestDto;
import mustache.practice.domain.dto.ReviewAddResponseDto;
import mustache.practice.domain.entity.Hospital;
import mustache.practice.domain.entity.Review;
import mustache.practice.repository.HospitalRepository;
import mustache.practice.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;

    public ReviewAddResponseDto add(ReviewAddRequestDto reviewAddRequestDto) {
        Optional<Hospital> optHospital = hospitalRepository.findById(reviewAddRequestDto.getHospitalId());
        Review saved = Review.builder().title(reviewAddRequestDto.getTitle())
                .content(reviewAddRequestDto.getContent()).patientName(reviewAddRequestDto.getPatientName()).hospital(optHospital.get()).build();

        Review savedReview = reviewRepository.save(saved);
        return ReviewAddResponseDto.builder().title(savedReview.getTitle()).patientName(savedReview.getPatientName()).content(savedReview.getContent()).message("리뷰 등록을 성공했습니다.").build();

    }
}
