package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.dto.response.*;
import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.entity.Scope;
import a503.daracbang.domain.diary.exception.DiaryNoPermissionException;
import a503.daracbang.domain.diary.exception.DiaryNotFoundException;
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
        List<Diary> scopeCheckedDiaryList = getViewableDiaryList(diaryRepository.findAllByMemberId(memberId), requesterId);
        return new DiaryListResponse(scopeCheckedDiaryList);
    }

    public MoodTrackerListResponse getMoodTracker(Long requesterId, Long memberId, int year, int month) {
        if(requesterId.equals(memberId))
            return new MoodTrackerListResponse(diaryRepository.findByMemberIdAndCreatedAtYearAndMonth(memberId, year, month));
        List<Diary> scopeCheckedDiaryList = getViewableDiaryList(diaryRepository.findByMemberIdAndCreatedAtYearAndMonth(memberId, year, month), requesterId);
        return new MoodTrackerListResponse(scopeCheckedDiaryList);
    }

    public MoodStatusResponse getMoodStatus(Long memberId) {
        List<Diary> diaryList = diaryRepository.findByMemberIdOrderByCreatedAt(memberId);
        if(!diaryList.isEmpty()){
            return new MoodStatusResponse(diaryList.get(0));
        }
        return new MoodStatusResponse();
    }

    public RecentDiaryResponse getRecentDiary(Long requesterId, Long memberId){
        if(requesterId.equals(memberId)) {
            List<Diary> myDiaryList = diaryRepository.findAllByMemberId(memberId);
            return new RecentDiaryResponse(myDiaryList.get(myDiaryList.size()-1));
        }
        List<Diary> viewableDiaryList = getViewableDiaryList(diaryRepository.findAllByMemberId(memberId), requesterId);
        if(viewableDiaryList.isEmpty())
            return new RecentDiaryResponse();
        return new RecentDiaryResponse(viewableDiaryList.get(viewableDiaryList.size()-1));
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

    private List<Diary> getViewableDiaryList(List<Diary> diaryList, Long requesterId){
        List<Diary> filteredDiaryList = new ArrayList<>();
        for(Diary diary:diaryList){
            if(isViewableDiary(diary,requesterId))
                filteredDiaryList.add(diary);
        }
        return filteredDiaryList;
    }

}
