package a503.daracbang.domain.guestbook.controller;

import a503.daracbang.domain.bgm.controller.BgmController;
import a503.daracbang.domain.bgm.dto.response.YoutubeListResponse;
import a503.daracbang.domain.bgm.service.CreateBgmService;
import a503.daracbang.domain.bgm.service.DeleteBgmService;
import a503.daracbang.domain.bgm.service.FindBgmService;
import a503.daracbang.global.ApiDocsTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BgmController.class)
@SuppressWarnings("NonAsciiCharacters")
public class BgmTest extends ApiDocsTest {

    @MockBean
    private CreateBgmService createBgmService;

    @MockBean
    private DeleteBgmService deleteBgmService;

    @MockBean
    private FindBgmService findBgmService;

    @Test
    void BGM_유튜브_조회_성공() throws Exception {
        // given
        YoutubeListResponse listResponse = new YoutubeListResponse(null);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bgm/search")
                .contentType(MediaType.APPLICATION_JSON)                // request body 포맷
                .content(new ObjectMapper().writeValueAsString(listResponse)))  // request body 전달
//            .andDo(MockMvcResultHandlers.print())                 // 디버깅용 print 코드. 주석해도 됨
            // rest doc 에 보여지는 상단 이름이 "guestbooks/save" 이 됨
            .andDo(MockMvcRestDocumentation.document("bgm/search", // .adoc 에서 구분할 식별자
                Preprocessors.preprocessRequest(prettyPrint()),     // rest doc 이쁘게 출력하기
                Preprocessors.preprocessResponse(prettyPrint())))   // rest doc 이쁘게 출력하기
            .andExpect(status().isOk()); // 201 response 를 받아야 테스트를 통과한다.
    }
}
