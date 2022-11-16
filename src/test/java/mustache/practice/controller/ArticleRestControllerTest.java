package mustache.practice.controller;

import mustache.practice.domain.dto.ArticleResponse;
import mustache.practice.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    void json() throws Exception {
        ArticleResponse articleResponse = ArticleResponse.builder().id(1L).title("안녕").content("하세요").build();
        given(articleService.getArticle(1L))
                .willReturn(articleResponse);

        Long articleId = 1L;
        mockMvc.perform(get(String.format("/api/v1/articles/%d", articleId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("안녕"))
                .andDo(print());  //

    }

}