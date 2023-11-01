package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.exception.DiaryErrorCode;
import a503.daracbang.domain.diary.exception.DiaryNotFoundException;
import a503.daracbang.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteDiaryService {
    private final DiaryRepository diaryRepository;

    public void deleteDiary(Long memberId, Long diaryId) {
        Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
        if(diaryOptional.isPresent()){
            Diary diary = diaryOptional.get();
            diaryRepository.delete(diary);
        } else{
            throw new DiaryNotFoundException(DiaryErrorCode.NOTFOUND_DIARY);
        }
    }
}
