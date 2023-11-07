package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.dto.response.DiaryListResponse;
import a503.daracbang.domain.diary.dto.response.DiaryResponse;
import a503.daracbang.domain.diary.dto.response.MoodTrackerListResponse;
import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.entity.Scope;
import a503.daracbang.domain.diary.exception.DiaryErrorCode;
import a503.daracbang.domain.diary.exception.DiaryNoPermissionException;
import a503.daracbang.domain.diary.exception.DiaryNotFoundException;
import a503.daracbang.domain.diary.exception.DiaryNotWriterException;
import a503.daracbang.domain.diary.repository.DiaryRepository;
import a503.daracbang.domain.neighbor.service.NeighborService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindDiaryService {

    private final DiaryRepository diaryRepository;
    private final NeighborService neighborService;

    public DiaryResponse getDiary(Long requesterId, Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(()->new DiaryNotFoundException(DiaryErrorCode.NOTFOUND_DIARY));
        // todo: 다이어리 공개범위와 사용자 이웃여부 비교해서, 이웃공개나 비공개 처리하기
        Scope scope = diary.getScope();
        if(requesterId==diary.getMemberId())
            return new DiaryResponse(diary);
        if(scope.equals("PRIVATE"))
            throw new DiaryNoPermissionException(DiaryErrorCode.NOPERMISSION_DIARY);
        if(scope.equals("NEIGHBOR") && !neighborService.isNeighbor(requesterId, diary.getMemberId()))
            throw new DiaryNoPermissionException(DiaryErrorCode.NOPERMISSION_DIARY);
        return new DiaryResponse(diary);
    }

    public DiaryListResponse getDiaryList(Long requesterId, Long memberId) {
        List<Diary> diaryList = diaryRepository.findAllByMemberId(memberId);
        // todo: 다이어리 공개범위와 사용자 이웃여부 비교해서, 이웃공개나 비공개 처리하기
        return new DiaryListResponse(diaryList);
    }

    public MoodTrackerListResponse getMoodTracker(Long requesterId, Long memberId, int year, int month) {
        List<Diary> diaryList = diaryRepository.findByMemberIdAndCreatedAtYearAndMonth(memberId, year, month);
        // todo: 다이어리 공개범위와 사용자 이웃여부 비교해서, 이웃공개나 비공개 처리하기
        return new MoodTrackerListResponse(diaryList);
    }
}
