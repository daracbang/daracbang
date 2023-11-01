package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteDiaryService {
    private final DiaryRepository diaryRepository;
    public void deleteDiary(Long diaryId) {
    }
}
