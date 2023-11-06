package a503.daracbang.domain.comment.controller;

import a503.daracbang.domain.comment.dto.request.WriteCommentRequest;
import a503.daracbang.domain.comment.service.DeleteCommentService;
import a503.daracbang.domain.comment.service.FindCommentService;
import a503.daracbang.domain.comment.service.WriteCommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final WriteCommentService writeCommentService;
    private final FindCommentService findCommentService;
    private final DeleteCommentService deleteCommentService;

    @PostMapping("/{diaryId}")
    public ResponseEntity<?> writeComment(@RequestBody @Valid WriteCommentRequest writeCommentRequest, @PathVariable Long diaryId) throws JsonProcessingException {
        //  Long memberId = MemberContextHolder.memberIdHolder.get();
        Long memberId = 2L; // 테스트용
        writeCommentService.writeComment(memberId, diaryId, writeCommentRequest);
        return ResponseEntity.ok("댓글이 작성되었습니다.");
    }

    // 댓글 삭제

    // 댓글 조회
}
