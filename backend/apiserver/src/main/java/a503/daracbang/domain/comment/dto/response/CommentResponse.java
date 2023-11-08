package a503.daracbang.domain.comment.dto.response;

import a503.daracbang.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CommentResponse {

    private Long memberId;
    private Long diaryId;
    private String createdAt;
    private String content;
    private String sentimentResult; // 감정 분석 결과
    private String positiveProbability;
    private String neutralProbability;
    private String negativeProbability;

    @Builder
    public CommentResponse(Comment comment){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.memberId = comment.getMember().getId();
        this.diaryId = comment.getDiary().getId();
        this.createdAt = comment.getCreatedAt().format(formatter);
        this.content = comment.getContent();
        this.sentimentResult = comment.getSentiment().getSentimentResult();
        this.positiveProbability = comment.getSentiment().getPositiveProbability();
        this.neutralProbability = comment.getSentiment().getNeutralProbability();
        this.negativeProbability = comment.getSentiment().getNegativeProbability();
    }

}
