package mustache.practice.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MustacheController {

    @GetMapping(value = "/hi/{id}")
    public String mustacheCon(@PathVariable(name = "id") String id, Model model) {
        model.addAttribute("username","inkyu");
        model.addAttribute("id",id);
        return "greetings";
    }
}
