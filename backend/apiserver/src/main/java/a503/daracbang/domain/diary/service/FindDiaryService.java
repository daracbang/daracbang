package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.dto.response.DiaryListResponse;
import a503.daracbang.domain.diary.dto.response.DiaryResponse;
import a503.daracbang.domain.diary.dto.response.MoodTrackerListResponse;
import a503.daracbang.domain.diary.dto.response.MoodTrackerResponse;
import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.exception.DiaryErrorCode;
import a503.daracbang.domain.diary.exception.DiaryNotFoundException;
import a503.daracbang.domain.diary.exception.MoodTrackerNotFoundException;
import a503.daracbang.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindDiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryResponse getDiary(Long requesterId, Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(()->new DiaryNotFoundException(DiaryErrorCode.NOTFOUND_DIARY));
        // todo: 다이어리 공개범위와 사용자 이웃여부 비교해서, 이웃공개나 비공개 처리하기
        return new DiaryResponse(diary);
    }

    public DiaryListResponse getDiaryList(Long requesterId, Long memberId) {
        List<Diary> diaryList = diaryRepository.findAllByMemberId(memberId);
        if(diaryList.isEmpty())
            throw new DiaryNotFoundException(DiaryErrorCode.NOTFOUND_DIARY);
        // todo: 다이어리 공개범위와 사용자 이웃여부 비교해서, 이웃공개나 비공개 처리하기
        return new DiaryListResponse(diaryList);
    }

    public MoodTrackerListResponse getMoodTracker(Long requesterId, Long memberId, int year, int month) {
        List<Diary> diaryList = diaryRepository.findByMemberIdAndCreatedAtYearAndMonth(memberId, year, month);
        if(diaryList.isEmpty())
            throw new MoodTrackerNotFoundException(DiaryErrorCode.NOTFOUND_MOODTRACKER);
        // todo: 다이어리 공개범위와 사용자 이웃여부 비교해서, 이웃공개나 비공개 처리하기
        return new MoodTrackerListResponse(diaryList);
    }
}
