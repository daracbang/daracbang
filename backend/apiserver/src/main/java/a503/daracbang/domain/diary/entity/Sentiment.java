package a503.daracbang.domain.diary.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Sentiment{
    private String sentimentResult; // 감정 분류 결과
    private String positiveProbability; // 긍정 확률
    private String neutralProbability; // 중립 확률
    private String negativeProbability; // 부정 확률
}
