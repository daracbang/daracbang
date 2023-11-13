package a503.daracbang.domain.diary.controller;

import a503.daracbang.domain.diary.dto.request.WriteDiaryRequest;
import a503.daracbang.domain.diary.dto.response.*;
import a503.daracbang.domain.diary.exception.DiaryErrorCode;
import a503.daracbang.domain.diary.exception.ScopeNotExistsException;
import a503.daracbang.domain.diary.service.DeleteDiaryService;
import a503.daracbang.domain.diary.service.FindDiaryService;
import a503.daracbang.domain.diary.service.WriteDiaryService;
import a503.daracbang.domain.member.util.MemberContextHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diaries")
public class DiaryController {

    private final WriteDiaryService writeDiaryService;
    private final FindDiaryService findDiaryService;
    private final DeleteDiaryService deleteDiaryService;

    @PostMapping()
    public ResponseEntity<?> writeDiary(@RequestBody @Valid WriteDiaryRequest writeDiaryRequest) throws JsonProcessingException {
        Long memberId = MemberContextHolder.memberIdHolder.get();
        writeDiaryService.writeDiary(memberId, writeDiaryRequest);
        return ResponseEntity.ok("다이어리가 작성되었습니다.");
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<?> deleteDiary(@PathVariable long diaryId){
        Long memberId = MemberContextHolder.memberIdHolder.get();
        deleteDiaryService.deleteDiary(memberId, diaryId);
        return ResponseEntity.ok("다이어리가 삭제되었습니다.");
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<?> getDiary(@PathVariable Long diaryId){
        Long requesterId = MemberContextHolder.memberIdHolder.get();
        DiaryResponse diaryResponse = findDiaryService.getDiary(requesterId, diaryId);
        return ResponseEntity.ok(diaryResponse);
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<?> getDiaryList(@PathVariable Long memberId){
        Long requesterId = MemberContextHolder.memberIdHolder.get();
        DiaryListResponse diaryListResponse = findDiaryService.getDiaryList(requesterId, memberId);
        return ResponseEntity.ok(diaryListResponse);
    }


    @GetMapping("/mood-tracker/{memberId}")
    public ResponseEntity<?> getMoodTracker(@PathVariable Long memberId, @RequestParam("year") int year, @RequestParam("month") int month){
        Long requesterId = MemberContextHolder.memberIdHolder.get();
        MoodTrackerListResponse moodTrackerListResponse = findDiaryService.getMoodTracker(requesterId, memberId, year, month);
        return ResponseEntity.ok(moodTrackerListResponse);
    }

    @GetMapping("/mood-status/{memberId}")
    public ResponseEntity<?> getMoodStatus(@PathVariable Long memberId){
        MoodStatusResponse moodStatusResponse = findDiaryService.getMoodStatus(memberId);
        return ResponseEntity.ok(moodStatusResponse);
    }

    @GetMapping("/recent/{memberId}")
    public ResponseEntity<?> getRecentDiary(@PathVariable Long memberId){
        Long requesterId = MemberContextHolder.memberIdHolder.get();
        RecentDiaryResponse recentDiaryResponse = findDiaryService.getRecentDiary(requesterId, memberId);
        return ResponseEntity.ok(recentDiaryResponse);
    }
}
