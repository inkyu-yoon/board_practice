package mustache.practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mustache.practice.domain.entity.Hospital;
import mustache.practice.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalRepository hospitalRepository;


    @GetMapping("/{id}/find")
    public String findById(@PathVariable(name = "id") Long id, Model model) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id);
        if (!optHospital.isEmpty()) {
            model.addAttribute("hospital", optHospital.get());
            System.out.println(optHospital.get());
            return "find";
        } else {
            return "error";
        }
    }



    @GetMapping("")
    public String searchList(@RequestParam(required = false) String keyword, Model model, Pageable pageable) {
        Page<Hospital> hospitals;
        if (keyword == null) {
            hospitals = hospitalRepository.findAll(pageable);
        } else {
            hospitals = hospitalRepository.findByRoadNameAddressContaining(keyword, pageable);
        }
        log.info("키워드:{}", keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospital/search";
    }
}
