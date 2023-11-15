package a503.daracbang.domain.diary.dto.response;

import a503.daracbang.domain.diary.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class RecentDiaryResponse {
    private String createdAt;
    private String content;

    @Builder
    public RecentDiaryResponse(Diary diary){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.createdAt = diary.getCreatedAt().format(formatter);
        this.content = diary.getContent();
    }
}
