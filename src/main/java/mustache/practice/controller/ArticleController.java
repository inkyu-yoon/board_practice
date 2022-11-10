package mustache.practice.controller;

import lombok.extern.slf4j.Slf4j;
import mustache.practice.domain.ArticleDto;
import mustache.practice.domain.entity.Article;
import mustache.practice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(value = "/new")
    public String createPage() {
        return "articles/new";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("")
    public String show(){
        return "redirect:/articles/list";
    }

    @GetMapping(value = "/{id}")
    public String selectSingle(@PathVariable(name = "id") Long id, Model model) {

        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(@PathVariable(name = "id") Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "articles/edit";
        } else {
            model.addAttribute("message", String.format("%d가 없습니다.", id));
            return "articles/error";
        }
    }

    @GetMapping("/{id}/delete")
    public String edit(@PathVariable(name = "id") Long id) {
        articleRepository.deleteById(id);
        return  "redirect:/articles";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable(name = "id") Long id, ArticleDto articleDto,Model model) {
        log.info("{} {} ", articleDto.getTitle(), articleDto.getContents());
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", article);
        return "redirect:/articles/"+article.getId();
    }



    @PostMapping("")
    public String add(ArticleDto articleDto) {
        log.info("{} {} ", articleDto.getTitle(), articleDto.getContents());
        Article savedArticle = articleRepository.save((articleDto.toEntity()));
        log.info("generatedId:{}", savedArticle.getId());
        return "redirect:/articles/" + savedArticle.getId();
    }


}
