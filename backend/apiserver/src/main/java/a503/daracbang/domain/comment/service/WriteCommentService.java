package a503.daracbang.domain.comment.service;

import a503.daracbang.domain.comment.dto.request.WriteCommentRequest;
import a503.daracbang.domain.comment.entity.Comment;
import a503.daracbang.domain.comment.repository.CommentRepositoty;
import a503.daracbang.domain.diary.dto.response.SentimentResponse;
import a503.daracbang.domain.diary.service.AnalysisSentimentService;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.exception.MemberErrorCode;
import a503.daracbang.domain.member.repository.MemberRepository;
import a503.daracbang.global.exception.CustomException;
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
    private final MemberRepository memberRepository;

    public void writeComment(Long memberId, Long diaryId, WriteCommentRequest writeCommentRequest) throws JsonProcessingException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));
        Comment comment = writeCommentRequest.toEntity(member, diaryId);
        commentRepositoty.save(comment);
        SentimentResponse sentimentResponse = analysisSentimentService.requestCLOVA(comment.getContent());
        comment.addSentiment(sentimentResponse);
        commentRepositoty.save(comment);
    }
}
