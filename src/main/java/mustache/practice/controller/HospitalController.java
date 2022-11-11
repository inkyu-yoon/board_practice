package mustache.practice.controller;

import lombok.extern.slf4j.Slf4j;
import mustache.practice.domain.entity.Hospital;
import mustache.practice.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepository;


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


}
