package mustache.practice.controller;

import lombok.RequiredArgsConstructor;
import mustache.practice.domain.dto.ArticleAddRequest;
import mustache.practice.domain.dto.ArticleResponse;
import mustache.practice.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleRestController {

    private  final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> get(@PathVariable(name = "id") Long id) {
        ArticleResponse articleResponse = articleService.getArticle(id);
        return ResponseEntity.ok().body(articleResponse);
    }

    @PostMapping
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody ArticleAddRequest dto) {
        return ResponseEntity.ok().body(articleService.addArticle(dto));
    }
}
