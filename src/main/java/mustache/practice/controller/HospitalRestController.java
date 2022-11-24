package mustache.practice.controller;

import lombok.RequiredArgsConstructor;
import mustache.practice.domain.dto.BookResponseDto;
import mustache.practice.domain.dto.HospitalResponse;
import mustache.practice.domain.dto.ReviewAddRequestDto;
import mustache.practice.domain.dto.ReviewAddResponseDto;
import mustache.practice.domain.entity.Hospital;
import mustache.practice.repository.HospitalRepository;
import mustache.practice.service.HospitalService;
import mustache.practice.service.ReviewService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospitals")
@RequiredArgsConstructor
public class HospitalRestController {
    private final HospitalService hospitalService;
    private final ReviewService reviewService;
    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewAddResponseDto> get(@PathVariable Long id, @RequestBody ReviewAddRequestDto reviewAddRequestDto) {
        return ResponseEntity.ok().body(reviewService.add(reviewAddRequestDto));
    }
    @GetMapping("")
    public ResponseEntity<List<HospitalResponse>> list(Pageable pageable) {
        return ResponseEntity.ok().body(hospitalService.findHospitals(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable(name = "id") Long id) {
        HospitalResponse hospitalResponse = hospitalService.getHospital(id);
        return ResponseEntity.ok().body(hospitalResponse);
    }

}
