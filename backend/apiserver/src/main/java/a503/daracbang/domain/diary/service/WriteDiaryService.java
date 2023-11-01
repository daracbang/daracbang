package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.dto.request.WriteDiaryRequest;
import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteDiaryService {

    private final DiaryRepository diaryRepository;

    public void writeDiary(WriteDiaryRequest writeDiaryRequest) {
        Diary diary = writeDiaryRequest.toEntity();
        // todo : 감정 분석
        diaryRepository.save(diary);
    }
}
