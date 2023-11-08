package a503.daracbang.domain.comment.service;

import a503.daracbang.domain.comment.entity.Comment;
import a503.daracbang.domain.comment.exception.CommentErrorCode;
import a503.daracbang.domain.comment.exception.CommentNotFoundException;
import a503.daracbang.domain.comment.repository.CommentRepositoty;
import a503.daracbang.domain.diary.exception.DiaryNotWriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteCommentService {

    private final CommentRepositoty commentRepositoty;

    public void deleteComment(Long memberId, Long commentId) {
        Comment comment = commentRepositoty.findById(commentId)
                .orElseThrow(()->new CommentNotFoundException(CommentErrorCode.NOTFOUND_COMMENT));
        if(memberId!=comment.getMember().getId())
            throw new DiaryNotWriterException(CommentErrorCode.NOTWRITER_COMMENT);
        commentRepositoty.delete(comment);
    }
}
