package a503.daracbang.domain.bgm.controller;

import a503.daracbang.config.WebConfig;
import a503.daracbang.domain.bgm.dto.response.YoutubeListResponse;
import a503.daracbang.domain.bgm.dto.response.YoutubeResponse;
import a503.daracbang.domain.bgm.service.CreateBgmService;
import a503.daracbang.domain.bgm.service.DeleteBgmService;
import a503.daracbang.domain.bgm.service.FindBgmService;
import a503.daracbang.domain.member.interceptor.ValidTokenInterceptor;
import a503.daracbang.domain.member.util.JwtUtil;
import a503.daracbang.global.ApiDocsTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = BgmController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebConfig.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ValidTokenInterceptor.class),
    })
@SuppressWarnings("NonAsciiCharacters")
public class BgmTest extends ApiDocsTest {

    @MockBean
    private CreateBgmService createBgmService;

    @MockBean
    private DeleteBgmService deleteBgmService;

    @MockBean
    private FindBgmService findBgmService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void BGM_유튜브_조회_성공() throws Exception {
        // given
        String q = "hypeboy";
        YoutubeResponse youtubeResponse1 = new YoutubeResponse("ghrlZIMDzbM", "Hype Boy");
        YoutubeResponse youtubeResponse2 = new YoutubeResponse("kdOS94IjzzE", "Attention");
        List<YoutubeResponse> youtubeResponses = Arrays.asList(youtubeResponse1, youtubeResponse2);
        YoutubeListResponse listResponse = new YoutubeListResponse(youtubeResponses);
        String s = "mockJwtToken";

        // when
        when(jwtUtil.generateJwt(1L)).thenReturn(s);
        when(findBgmService.findYoutube(q)).thenReturn(listResponse);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bgms/search?q=" + q)
                .header("Authorization", s)
                .contentType(MediaType.APPLICATION_JSON))
//            .andDo(MockMvcResultHandlers.print())                 // 디버깅용 print 코드. 주석해도 됨
            .andDo(MockMvcRestDocumentation.document("bgms/search",
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint())))
            .andExpect(status().isOk());
    }
}
