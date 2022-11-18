package mustache.practice.controller;

import lombok.extern.slf4j.Slf4j;
import mustache.practice.domain.ArticleDto;
import mustache.practice.domain.CommentDto;
import mustache.practice.domain.entity.Article;
import mustache.practice.domain.entity.Comment;
import mustache.practice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("")
    public String add(CommentDto commentDto) {
        log.info("{} {} {}", commentDto.getUser(), commentDto.getContent(),commentDto.getArticle());
        Comment savedComment = commentRepository.save((commentDto.toEntity()));
        return "redirect:/articles/" + savedComment.getArticle().getId();
    }
}
