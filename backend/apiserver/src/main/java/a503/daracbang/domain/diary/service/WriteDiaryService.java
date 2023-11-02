package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.dto.request.WriteDiaryRequest;
import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.exception.DiaryAlreadyWrittenException;
import a503.daracbang.domain.diary.exception.DiaryErrorCode;
import a503.daracbang.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WriteDiaryService {

    private final DiaryRepository diaryRepository;

    private final OneDayOneWritePolicy oneDayOneWritePolicy;

    public void writeDiary(Long memberId, WriteDiaryRequest writeDiaryRequest) {
        // 사용자가 오늘 다이어리를 작성했는지 검증
        if(!oneDayOneWritePolicy.verify(memberId, LocalDate.now()))
            throw new DiaryAlreadyWrittenException(DiaryErrorCode.ALREADYWRITTEN_DIARY);
        // 작성하지 않은 경우에만 저장
        Diary diary = writeDiaryRequest.toEntity(memberId);
        diaryRepository.save(diary);
        // todo : 감정 분석
    }
}
