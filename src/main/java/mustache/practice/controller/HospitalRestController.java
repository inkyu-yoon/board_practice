package mustache.practice.controller;

import lombok.RequiredArgsConstructor;
import mustache.practice.domain.dto.BookResponseDto;
import mustache.practice.domain.dto.HospitalResponse;
import mustache.practice.domain.entity.Hospital;
import mustache.practice.repository.HospitalRepository;
import mustache.practice.service.HospitalService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospitals")
@RequiredArgsConstructor
public class HospitalRestController {
    private final HospitalService hospitalService;

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
