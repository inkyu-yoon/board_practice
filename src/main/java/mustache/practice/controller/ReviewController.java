package mustache.practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mustache.practice.domain.ArticleDto;
import mustache.practice.domain.dto.ReviewDto;
import mustache.practice.domain.entity.Article;
import mustache.practice.domain.entity.Review;
import mustache.practice.repository.ArticleRepository;
import mustache.practice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @PostMapping("")
    public String add(ReviewDto reviewDto) {
        log.info("{} {} {} ",reviewDto.getPatientName(), reviewDto.getTitle(), reviewDto.getContent());
       reviewRepository.save((reviewDto.toEntity()));

        return "redirect:/hospitals" ;
    }
}
