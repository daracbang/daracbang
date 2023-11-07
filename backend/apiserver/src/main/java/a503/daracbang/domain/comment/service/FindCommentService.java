package a503.daracbang.domain.comment.service;

import a503.daracbang.domain.comment.dto.response.CommentListResponse;
import a503.daracbang.domain.comment.entity.Comment;
import a503.daracbang.domain.comment.repository.CommentRepositoty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindCommentService {

    private final CommentRepositoty commentRepositoty;
    public CommentListResponse getCommentList(Long diaryId) {
        List<Comment> commentList =commentRepositoty.findAllByDiaryId(diaryId);
        return new CommentListResponse(commentList);
    }
}
