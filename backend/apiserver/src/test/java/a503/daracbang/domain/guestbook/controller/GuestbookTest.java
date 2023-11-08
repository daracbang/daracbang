package a503.daracbang.domain.guestbook.controller;

import a503.daracbang.config.WebConfig;
import a503.daracbang.domain.guestbook.controller.GuestbookController;
import a503.daracbang.domain.guestbook.dto.request.RegisterGuestbookRequest;
import a503.daracbang.domain.guestbook.dto.response.GuestbookListResponse;
import a503.daracbang.domain.guestbook.dto.response.GuestbookResponse;
import a503.daracbang.domain.guestbook.service.CreateGuestbookService;
import a503.daracbang.domain.guestbook.service.DeleteGuestbookService;
import a503.daracbang.domain.guestbook.service.FindGuestBookService;
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
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = GuestbookController.class,
	excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebConfig.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ValidTokenInterceptor.class)
	})
@SuppressWarnings("NonAsciiCharacters")
class GuestbookTest extends ApiDocsTest {

    @MockBean // GuestbookController이 의존하는 빈을 모킹
    private CreateGuestbookService createGuestbookService;

    @MockBean
    private DeleteGuestbookService deleteGuestbookService;

    @MockBean
    private FindGuestBookService findGuestBookService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void 방명록_생성_성공() throws Exception {
        // given
        RegisterGuestbookRequest form = new RegisterGuestbookRequest("test");
        doNothing().when(createGuestbookService).save(1L, form);
        String s = "mockJwtToken";

        // when & then
        when(jwtUtil.generateJwt(1L)).thenReturn(s);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/guestbooks/1")
                .header("Authorization", s)
                .contentType(MediaType.APPLICATION_JSON)                // request body 포맷
                .content(new ObjectMapper().writeValueAsString(form)))  // request body 전달
//            .andDo(MockMvcResultHandlers.print())                 // 디버깅용 print 코드. 주석해도 됨
            // rest doc 에 보여지는 상단 이름이 "guestbooks/save" 이 됨
            .andDo(MockMvcRestDocumentation.document("guestbooks/save", // .adoc 에서 구분할 식별자
                Preprocessors.preprocessRequest(prettyPrint()),     // rest doc 이쁘게 출력하기
                Preprocessors.preprocessResponse(prettyPrint())))   // rest doc 이쁘게 출력하기
            .andExpect(status().isCreated()); // 201 response 를 받아야 테스트를 통과한다.
    }

    @Test
    void 방명록_삭제_성공() throws Exception {
        // given
        doNothing().when(deleteGuestbookService).delete(1L, 1L);
        String s = "mockJwtToken";

        // when & then
        when(jwtUtil.generateJwt(1L)).thenReturn(s);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/guestbooks/1")
                .header("Authorization", s)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcRestDocumentation.document("guestbooks/delete",
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint())))
            .andExpect(status().isNoContent());
    }

    @Test
    void 방명록_페이지네이션() throws Exception {
        // given
        List<GuestbookResponse> guestbooks = new ArrayList<>();
        guestbooks.add(new GuestbookResponse(1L, "nickname1", "profileImage1", "content1"));
        guestbooks.add(new GuestbookResponse(2L, "nickname2", "profileImage2", "content2"));
        GuestbookListResponse responses = new GuestbookListResponse(guestbooks);
        String s = "mockJwtToken";

        // when
        when(jwtUtil.generateJwt(1L)).thenReturn(s);
        when(findGuestBookService.getGuestbooks(1L, 0)).thenReturn(responses);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/guestbooks/1?lastId=0")
                .header("Authorization", s)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcRestDocumentation.document("guestbooks/pagination",
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint())))
            .andExpect(status().isOk())
        // 응답 본문의 형태를 검증합니다.
        .andExpect(jsonPath("$.guestbooks[0].memberId").value(1L))
            .andExpect(jsonPath("$.guestbooks[0].nickname").value("nickname1"))
            .andExpect(jsonPath("$.guestbooks[0].profileImage").value("profileImage1"))
            .andExpect(jsonPath("$.guestbooks[0].content").value("content1"))
            .andExpect(jsonPath("$.guestbooks[1].memberId").value(2L))
            .andExpect(jsonPath("$.guestbooks[1].nickname").value("nickname2"))
            .andExpect(jsonPath("$.guestbooks[1].profileImage").value("profileImage2"))
            .andExpect(jsonPath("$.guestbooks[1].content").value("content2"));
    }
}
