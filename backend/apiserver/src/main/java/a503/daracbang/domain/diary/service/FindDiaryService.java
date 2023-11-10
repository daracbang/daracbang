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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FindDiaryService {

    private final DiaryRepository diaryRepository;
    private final NeighborService neighborService;

    public DiaryResponse getDiary(Long requesterId, Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(DiaryNotFoundException::new);
        if(diary.isOwner(requesterId)){
            return new DiaryResponse(diary);
        }
        if(!isViewableDiary(diary, requesterId))
            throw new DiaryNoPermissionException();
        return new DiaryResponse(diary);
    }

    public DiaryListResponse getDiaryList(Long requesterId, Long memberId) {
        if(requesterId.equals(memberId))
            return new DiaryListResponse(diaryRepository.findAllByMemberId(memberId));
        List<Diary> scopeCheckedDiaryList = checkDiaryListScope(diaryRepository.findAllByMemberId(memberId), requesterId);
        return new DiaryListResponse(scopeCheckedDiaryList);
    }

    public MoodTrackerListResponse getMoodTracker(Long requesterId, Long memberId, int year, int month) {
        if(requesterId.equals(memberId))
            return new MoodTrackerListResponse(diaryRepository.findByMemberIdAndCreatedAtYearAndMonth(memberId, year, month));
        List<Diary> scopeCheckedDiaryList = checkDiaryListScope(diaryRepository.findByMemberIdAndCreatedAtYearAndMonth(memberId, year, month), requesterId);
        return new MoodTrackerListResponse(scopeCheckedDiaryList);
    }

    // 요청한 유저가 볼 수있는지 체크
    private boolean isViewableDiary(Diary diary, Long requesterId){
        Scope diaryScope = diary.getScope();
        if(Scope.PUBLIC.equals(diaryScope)){
            return true;
        }
        if(Scope.NEIGHBOR.equals(diaryScope)){
            return neighborService.isNeighbor(diary.getMember().getId(), requesterId);
        }
        return false;
    }

    private List<Diary> checkDiaryListScope(List<Diary> diaryList, Long requesterId){
        List<Diary> filteredDiaryList = new ArrayList<>();
        for(Diary diary:diaryList){
            if(isViewableDiary(diary,requesterId))
                filteredDiaryList.add(diary);
        }
        return filteredDiaryList;
    }
}
