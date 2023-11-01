package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.dto.response.DiaryListResponse;
import a503.daracbang.domain.diary.dto.response.DiaryResponse;
import a503.daracbang.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindDiaryService {

    private final DiaryRepository diaryRepository;
//    public static DiaryResponse getDiary(Long diaryId) {
//    }
//
//    public DiaryListResponse getDiaryList(Long memberId) {
//    }
//
//    public DiaryListResponse getMoodTracker(Long memberId, int month, int year) {
//    }
}
