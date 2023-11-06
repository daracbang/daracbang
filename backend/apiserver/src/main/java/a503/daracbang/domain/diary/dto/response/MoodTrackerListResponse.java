package a503.daracbang.domain.diary.dto.response;

import a503.daracbang.domain.diary.entity.Diary;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MoodTrackerListResponse {

    private List<MoodTrackerResponse> moodTracker;

    public MoodTrackerListResponse(List<Diary> diaryList){
        this.moodTracker = diaryList.stream().map(MoodTrackerResponse::new).collect(Collectors.toList());
    }
}
