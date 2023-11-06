package a503.daracbang.domain.diary.dto.response;


import a503.daracbang.domain.diary.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class MoodTrackerResponse {
    private Long diaryId;
    private String createdAt;
    private String sentiment;

    @Builder
    public MoodTrackerResponse(Diary entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.diaryId = entity.getId();
        this.createdAt = entity.getCreatedAt().format(formatter);
        this.sentiment = entity.getSentiment().getSentimentResult();
    }
}
