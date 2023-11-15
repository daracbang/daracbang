package a503.daracbang.domain.diary.entity;

import a503.daracbang.domain.diary.dto.response.SentimentResponse;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class Sentiment{
    private String sentimentResult; // 감정 분류 결과
    private String positiveProbability; // 긍정 확률
    private String neutralProbability; // 중립 확률
    private String negativeProbability; // 부정 확률

    @Builder
    public Sentiment(SentimentResponse response){
        this.sentimentResult = response.getDocument().getSentiment();
        this.positiveProbability = Float.toString(Math.round(response.getDocument().getConfidence().getPositive()*100)/100.0f)+"%";
        this.neutralProbability = Float.toString(Math.round(response.getDocument().getConfidence().getNeutral()*100)/100.0f)+"%";
        this.negativeProbability = Float.toString(Math.round(response.getDocument().getConfidence().getNegative()*100)/100.0f)+"%";
    }

    protected Sentiment() {
    }
}
