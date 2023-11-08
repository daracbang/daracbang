package a503.daracbang.domain.comment.service;

import a503.daracbang.domain.comment.dto.response.CommentListResponse;
import a503.daracbang.domain.comment.entity.Comment;
import a503.daracbang.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindCommentService {

    private final CommentRepository commentRepository;
    public CommentListResponse getCommentList(Long diaryId) {
        List<Comment> commentList =commentRepository.findAllByDiaryId(diaryId);
        return new CommentListResponse(commentList);
    }
}
