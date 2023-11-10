package a503.daracbang.domain.diary.dto.response;

import a503.daracbang.domain.diary.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MoodStatusResponse {
    private int positiveRate;
    private int neutralRate;
    private int negativeRate;

    @Builder
    public MoodStatusResponse(Diary diary){
        this.positiveRate = (int) Float.parseFloat(diary.getSentiment().getPositiveProbability().replace("%",""));
        this.neutralRate = (int) Float.parseFloat(diary.getSentiment().getNeutralProbability().replace("%",""));
        this.negativeRate = (int) Float.parseFloat(diary.getSentiment().getNegativeProbability().replace("%",""));
    }
}
