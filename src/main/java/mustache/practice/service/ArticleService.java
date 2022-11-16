package mustache.practice.service;

import mustache.practice.domain.dto.ArticleResponse;
import mustache.practice.domain.entity.Article;
import mustache.practice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse getArticle(Long id) {
        Optional<Article> optArticle = articleRepository.findById(id); // Entity
        Article article = optArticle.get();
        ArticleResponse articleResponse = Article.of(article); // DTO
        return articleResponse;
    }
}
