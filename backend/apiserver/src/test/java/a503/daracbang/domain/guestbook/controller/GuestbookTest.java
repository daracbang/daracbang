package a503.daracbang.domain.guestbook.controller;

import a503.daracbang.domain.guestbook.dto.request.GuestbookCreateForm;
import a503.daracbang.domain.guestbook.service.GuestbookService;
import a503.daracbang.global.ApiDocsTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@WebMvcTest(GuestbookController.class)
@SuppressWarnings("NonAsciiCharacters")
class GuestbookTest extends ApiDocsTest {

    @MockBean // GuestbookController이 의존하는 빈을 모킹
    private GuestbookService guestbookService;

    @Nested
    @DisplayName("방명록 생성")
    class WriteGuestbookDocs {

        @Test
        @DisplayName("방명록 생성 성공")
        void 방명록_생성테스트() throws Exception {
            // given
            GuestbookCreateForm form = new GuestbookCreateForm("test");
            doNothing().when(guestbookService).save(1L, form);

            // when & then
            mockMvc.perform(MockMvcRequestBuilders.post("/api/guestbooks/1")
                    .contentType(MediaType.APPLICATION_JSON)                // request body 포맷
                    .content(new ObjectMapper().writeValueAsString(form)))  // request body 전달
//            .andDo(MockMvcResultHandlers.print())                 // 디버깅용 print 코드. 주석해도 됨
                // rest doc 에 보여지는 상단 이름이 "guestbooks/save" 이 됨
                .andDo(MockMvcRestDocumentation.document("guestbooks/save", // .adoc 에서 구분할 식별자
                    Preprocessors.preprocessRequest(prettyPrint()),     // rest doc 이쁘게 출력하기
                    Preprocessors.preprocessResponse(prettyPrint())))   // rest doc 이쁘게 출력하기
                .andExpect(MockMvcResultMatchers.status().isCreated()); // 201 response 를 받아야 테스트를 통과한다.
        }
    }

    @Nested
    @DisplayName("방명록 삭제")
    class DeleteGuestbookDocs {

        @Test
        @DisplayName("방명록 삭제 성공")
        void 방명록_삭제테스트() throws Exception {
            // given
            doNothing().when(guestbookService).delete(1L, 1L);

            // when & then
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/guestbooks/1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcRestDocumentation.document("guestbooks/delete",
                    Preprocessors.preprocessRequest(prettyPrint()),
                    Preprocessors.preprocessResponse(prettyPrint())))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        }
    }
}
