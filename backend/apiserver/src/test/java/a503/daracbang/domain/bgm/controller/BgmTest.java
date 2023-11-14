package a503.daracbang.domain.bgm.controller;

import a503.daracbang.config.WebConfig;
import a503.daracbang.domain.bgm.dto.request.RegisterBgmRequest;
import a503.daracbang.domain.bgm.dto.response.MyBgmListResponse;
import a503.daracbang.domain.bgm.dto.response.MyBgmResponse;
import a503.daracbang.domain.bgm.dto.response.YoutubeListResponse;
import a503.daracbang.domain.bgm.dto.response.YoutubeResponse;
import a503.daracbang.domain.bgm.entity.Bgm;
import a503.daracbang.domain.bgm.service.CreateBgmService;
import a503.daracbang.domain.bgm.service.DeleteBgmService;
import a503.daracbang.domain.bgm.service.FindBgmService;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.interceptor.ValidTokenInterceptor;
import a503.daracbang.domain.member.repository.MemberRepository;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
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
    private MemberRepository memberRepository;

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
        String jwt = "mockJwtToken";

        // when
        when(jwtUtil.generateJwt(1L)).thenReturn(jwt);
        when(findBgmService.findYoutube(q)).thenReturn(listResponse);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bgms/search?q=" + q)
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON))
//            .andDo(MockMvcResultHandlers.print())                 // 디버깅용 print 코드. 주석해도 됨
            .andDo(MockMvcRestDocumentation.document("/api/bgms/search",
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint())))
            .andExpect(status().isOk());
    }

    @Test
    void BGM_등록_videoId_버전() throws Exception {
        // given
        RegisterBgmRequest req = new RegisterBgmRequest("videoId", "bgmName");
        String jwt = "mockJwtToken";

        // when
        when(jwtUtil.generateJwt(1L)).thenReturn(jwt);
        doNothing().when(createBgmService).saveBgm(req, 1L);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/bgms/1")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(req)))
            .andDo(MockMvcRestDocumentation.document("/api/bgms",
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint())))
            .andExpect(status().isCreated());
    }

    @Test
    void 내가_등록한_BGM_보기() throws Exception {
        // given
        MyBgmResponse response1 = new MyBgmResponse(bgm1_만들기(멤버_만들기()));
        MyBgmResponse response2 = new MyBgmResponse(bgm2_만들기(멤버_만들기()));
        MyBgmListResponse responseList = new MyBgmListResponse(Arrays.asList(response1, response2));
        String jwt = "mockJwtToken";

        // when
        when(jwtUtil.generateJwt(1L)).thenReturn(jwt);
        when(findBgmService.getMyBgms(1L)).thenReturn(responseList);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bgms/1")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcRestDocumentation.document("/api/bgms/my",
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint())))
            .andExpect(status().isOk());
    }

    @Test
    void BGM_삭제() throws Exception {
        // given
        Bgm bgm = bgm1_만들기(멤버_만들기());
        String jwt = "mockJwtToken";

        // when
        when(jwtUtil.generateJwt(1L)).thenReturn(jwt);
        doNothing().when(deleteBgmService).delete(bgm.getId(), 1L);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/bgms/1")
                .header("Authorization", jwt))
            .andDo(MockMvcRestDocumentation.document("/api/bgms/delete",
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint())))
            .andExpect(status().isNoContent());
        
    }

    private Bgm bgm1_만들기(Member member) {
        return new Bgm(member, "bgmName1", "url1");
    }

    private Bgm bgm2_만들기(Member member) {
        return new Bgm(member, "bgmName2",  "url2");
    }

    private Member 멤버_만들기() {
        return Member.builder()
            .loginId("loginId")
            .password("password")
            .nickname("nickname")
            .profileImage("profileImage")
            .build();
    }
}
