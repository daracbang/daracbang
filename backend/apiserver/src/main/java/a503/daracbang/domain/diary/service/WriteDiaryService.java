package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.dto.request.WriteDiaryRequest;
import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.exception.DiaryAlreadyWrittenException;
import a503.daracbang.domain.diary.exception.DiaryErrorCode;
import a503.daracbang.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WriteDiaryService {

    private final DiaryRepository diaryRepository;

    private final DiaryTimePolicy diaryTimePolicy;

    public void writeDiary(Long memberId, WriteDiaryRequest writeDiaryRequest) {
        if(!diaryTimePolicy.verify(memberId, LocalDate.now()))
            throw new DiaryAlreadyWrittenException(DiaryErrorCode.ALREADYWRITTEN_DIARY);
        Diary diary = writeDiaryRequest.toEntity(memberId);
        diaryRepository.save(diary);
        // todo : 감정 분석
    }
}
