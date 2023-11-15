package a503.daracbang.domain.diary.dto.response;

import a503.daracbang.domain.diary.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class DiaryListResponse {

    private List<DiaryResponse> diaryList;

    public DiaryListResponse(List<Diary> diaryList){
        this.diaryList = diaryList.stream().map(DiaryResponse::new).collect(Collectors.toList());
    }
}
