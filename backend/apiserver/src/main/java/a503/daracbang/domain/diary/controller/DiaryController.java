package a503.daracbang.domain.diary.controller;

import a503.daracbang.domain.diary.dto.request.WriteDiaryRequest;
import a503.daracbang.domain.diary.dto.response.DiaryListResponse;
import a503.daracbang.domain.diary.dto.response.DiaryResponse;
import a503.daracbang.domain.diary.dto.response.MoodTrackerListResponse;
import a503.daracbang.domain.diary.exception.DiaryErrorCode;
import a503.daracbang.domain.diary.exception.ScopeNotExistsException;
import a503.daracbang.domain.diary.service.DeleteDiaryService;
import a503.daracbang.domain.diary.service.FindDiaryService;
import a503.daracbang.domain.diary.service.WriteDiaryService;
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
//      Long memberId = MemberContextHolder.memberIdHolder.get();
        Long memberId = 2L; // 테스트용
        writeDiaryService.writeDiary(memberId, writeDiaryRequest);
        return ResponseEntity.ok("다이어리가 작성되었습니다.");
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<?> deleteDiary(@PathVariable long diaryId){
        //    Long memberId = MemberContextHolder.memberIdHolder.get();
        long memberId = 2L; // 테스트용
        deleteDiaryService.deleteDiary(memberId, diaryId);
        return ResponseEntity.ok("다이어리가 삭제되었습니다.");
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<?> getDiary(@PathVariable Long diaryId){
        //  Long requesterId = MemberContextHolder.memberIdHolder.get();
        Long requesterId = 2L; // 다이어리 조회 요청자 ID (테스트용)
        DiaryResponse diaryResponse = findDiaryService.getDiary(requesterId, diaryId);
        return ResponseEntity.ok(diaryResponse);
    }


    @GetMapping("/list/{memberId}")
    public ResponseEntity<?> getDiaryList(@PathVariable Long memberId){
        //  Long requesterId = MemberContextHolder.memberIdHolder.get();
        Long requesterId = 2L; // // 다이어리 리스트 조회 요청자 ID (테스트용)
        DiaryListResponse diaryListResponse = findDiaryService.getDiaryList(requesterId, memberId);
        return ResponseEntity.ok(diaryListResponse);
    }


    @GetMapping("/mood-tracker/{memberId}")
    public ResponseEntity<?> getMoodTracker(@PathVariable Long memberId, @RequestParam("year") int year, @RequestParam("month") int month){
        //  Long requesterId = MemberContextHolder.memberIdHolder.get();
        Long requesterId = 2L; // // 무드트래커 조회 요청자 ID (테스트용)
        MoodTrackerListResponse moodTrackerListResponse = findDiaryService.getMoodTracker(requesterId, memberId, year, month);
        return ResponseEntity.ok(moodTrackerListResponse);
    }
}
