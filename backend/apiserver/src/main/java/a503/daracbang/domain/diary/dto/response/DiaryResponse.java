package a503.daracbang.domain.diary.dto.response;


import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.entity.Scope;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@NoArgsConstructor
public class DiaryResponse {

    private Long id;
    private Scope scope;
    private String createdAt;
    private String content;
    private String sentimentResult; // 감정 분석 결과
    private String positiveProbability;
    private String neutralProbability;
    private String negativeProbability;

    @Builder
    public DiaryResponse(Diary entity){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.id = entity.getId();
        this.scope = entity.getScope();
        this.createdAt = entity.getCreatedAt().format(formatter);
        this.content = entity.getContent();
        this.sentimentResult = entity.getSentiment().getSentimentResult();
        this.positiveProbability = entity.getSentiment().getPositiveProbability();
        this.neutralProbability = entity.getSentiment().getNeutralProbability();
        this.negativeProbability = entity.getSentiment().getNegativeProbability();
    }

}
