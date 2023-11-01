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

    public void writeDiary(Long memberId, WriteDiaryRequest writeDiaryRequest) {
        // todo : 감정 분석
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0,0,0));
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        List<Diary> alreadyWrittenDiary = diaryRepository.findAllByMemberIdAndCreatedAtBetween(memberId,startDatetime,endDatetime);
        // 이미 같은 날 다이어리를 작성한 사용자인 경우 에러 발생
        if(!alreadyWrittenDiary.isEmpty())
            throw new DiaryAlreadyWrittenException(DiaryErrorCode.ALREADYWRITTEN_DIARY);
        // 작성하지 않은 경우에만 저장
        else{
            Diary diary = writeDiaryRequest.toEntity(memberId);
            diaryRepository.save(diary);
        }
    }
}
