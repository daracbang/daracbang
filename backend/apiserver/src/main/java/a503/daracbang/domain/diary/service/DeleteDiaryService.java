package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.exception.DiaryErrorCode;
import a503.daracbang.domain.diary.exception.DiaryNotFoundException;
import a503.daracbang.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteDiaryService {
    private final DiaryRepository diaryRepository;

    public void deleteDiary(Long memberId, Long diaryId) {
        Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
        if(diaryOptional.isPresent()){
            Diary diary = diaryOptional.get();
            Long diaryMemberId = diary.getMemberId();
            // 삭제를 요청한 멤버 아이디와 다이어리의 멤버 아이디가 다른 경우
            if(!Objects.equals(diaryMemberId, memberId))
                throw new DiaryNotFoundException(DiaryErrorCode.NOTWRITER_DIARY);
            diaryRepository.delete(diary);
        } else{
            throw new DiaryNotFoundException(DiaryErrorCode.NOTFOUND_DIARY);
        }
    }
}
