package a503.daracbang.domain.guestbook.controller;

import a503.daracbang.domain.guestbook.dto.request.GuestbookCreateForm;
import a503.daracbang.domain.guestbook.service.GuestbookService;
import a503.daracbang.global.ApiDocsTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@WebMvcTest(GuestbookController.class)
class GuestbookControllerTest extends ApiDocsTest {

    @MockBean // GuestbookController이 의존하는 빈을 모킹
    private GuestbookService guestbookService;

    static final String URI_PATH = "/api/guestbooks/1";
    static final String DOCS_TAG = "guestbooks/save";
    
    @Test
    void 레독_테스트() throws Exception {
        // given
        GuestbookCreateForm form = new GuestbookCreateForm("test");
        doNothing().when(guestbookService).save(1L, form);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post(URI_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(form)))
            .andDo(MockMvcResultHandlers.print())
            .andDo(MockMvcRestDocumentation.document(DOCS_TAG,
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint())))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
