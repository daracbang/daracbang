package a503.daracbang.domain.comment.service;

import a503.daracbang.domain.comment.dto.request.WriteCommentRequest;
import a503.daracbang.domain.comment.entity.Comment;
import a503.daracbang.domain.comment.repository.CommentRepositoty;
import a503.daracbang.domain.diary.dto.response.SentimentResponse;
import a503.daracbang.domain.diary.service.AnalysisSentimentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WriteCommentService {

    private final CommentRepositoty commentRepositoty;
    private final AnalysisSentimentService analysisSentimentService;

    public void writeComment(Long memberId, Long diaryId, WriteCommentRequest writeCommentRequest) throws JsonProcessingException {
        Comment comment = writeCommentRequest.toEntity(memberId, diaryId);
        commentRepositoty.save(comment);
        SentimentResponse sentimentResponse = analysisSentimentService.requestCLOVA(comment.getContent());
        comment.addSentiment(sentimentResponse);
        commentRepositoty.save(comment);
    }
}
