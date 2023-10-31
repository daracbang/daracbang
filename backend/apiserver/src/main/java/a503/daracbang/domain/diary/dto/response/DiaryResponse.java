package a503.daracbang.domain.diary.dto.response;


import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.border.EmptyBorder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryResponse {

    private Long id;
    private String content;
    private String sentimentResult; // 감정 분석 결과
    private String positiveProbability;
    private String neutralProbability;
    private String negativeProbability;

    @Builder
    private DiaryResponse(Diary entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.sentimentResult = entity.getSentiment().getSentimentResult();
        this.positiveProbability = entity.getSentiment().getPositiveProbability();
        this.neutralProbability = entity.getSentiment().getNeutralProbability();
        this.negativeProbability = entity.getSentiment().getNegativeProbability();
    }

}
