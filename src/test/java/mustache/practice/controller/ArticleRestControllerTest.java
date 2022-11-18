package mustache.practice.controller;

import com.google.gson.Gson;
import mustache.practice.domain.dto.ArticleAddRequest;
import mustache.practice.domain.dto.ArticleResponse;
import mustache.practice.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//어떤 컨트롤러를 테스트할 것인지 명시하면, 테스트 환경이 가벼워 진다.
@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc; //컨트롤러 테스트에 필요한 객체

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("Article이 잘 등록 되는지")
    void addArticle() throws Exception {
        ArticleAddRequest articleAddRequest = new ArticleAddRequest("제목입니다.", "내용입니다.");
        Gson gson = new Gson();
        String content = gson.toJson(articleAddRequest);

        given(articleService.addArticle(articleAddRequest)).willReturn(new ArticleResponse(1L, articleAddRequest.getTitle(), articleAddRequest.getContent()));

        mockMvc.perform(post("/api/v1/articles").contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

    }

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