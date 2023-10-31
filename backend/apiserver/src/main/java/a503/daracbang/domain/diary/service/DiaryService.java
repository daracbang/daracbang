package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.dto.request.WriteDiaryRequest;
import a503.daracbang.domain.diary.dto.response.DiaryListResponse;
import a503.daracbang.domain.diary.dto.response.DiaryResponse;
import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public void writeDiary(WriteDiaryRequest writeDiaryRequest) {
        Diary diary = writeDiaryRequest.toEntity();
        // todo : 감정 분석
        diaryRepository.save(diary);
    }

    public void deleteDiary(@PathVariable Long diaryId) {

    }

    public DiaryResponse getDiary(Long diaryId) {
    }

    public DiaryListResponse getDiaryList(Long id) {
    }

    public DiaryListResponse getMoodTracker(Long memberId, int month, int year) {
    }
}
