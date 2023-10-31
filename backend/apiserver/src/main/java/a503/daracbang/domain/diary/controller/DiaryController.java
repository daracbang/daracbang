package a503.daracbang.domain.diary.controller;

import a503.daracbang.domain.diary.dto.request.WriteDiaryRequest;
import a503.daracbang.domain.diary.dto.response.DiaryListResponse;
import a503.daracbang.domain.diary.dto.response.DiaryResponse;
import a503.daracbang.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diaries")
public class DiaryController {

    private final DiaryService diaryService;
    //다이어리 작성
    @PostMapping()
    public ResponseEntity<?> writeDiary(@RequestBody WriteDiaryRequest writeDiaryRequest){
        diaryService.writeDiary(writeDiaryRequest);
        return ResponseEntity.ok("");
    }

    // 다이어리 삭제
    @DeleteMapping("/{diaryId}")
    public ResponseEntity<?> deleteDiary(@PathVariable Long diaryId){
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.ok("");
    }

    // 다이어리 상세 조회
    @GetMapping("/{diaryId}")
    public ResponseEntity<?> getDiary(@PathVariable Long diaryId){
        DiaryResponse diaryResponse = diaryService.getDiary(diaryId);
        return ResponseEntity.ok(diaryResponse);
    }

    // 전체 다이어리 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<?> getDiaryList(@PathVariable Long memberId){
        DiaryListResponse diaryListResponse = diaryService.getDiaryList(memberId);
        return ResponseEntity.ok(diaryListResponse);
    }

    // 무드 트래커 조회
    @GetMapping("/mood-tracker/{memberId}")
    public ResponseEntity<?> getMoodTracker(@PathVariable Long memberId, @RequestParam("year") int year, @RequestParam("month") int month){
        DiaryListResponse diaryListResponse = diaryService.getMoodTracker(memberId, month, year);
        return ResponseEntity.ok(diaryListResponse);
    }
}
