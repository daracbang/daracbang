package a503.daracbang.domain.diary.controller;

import a503.daracbang.domain.diary.dto.request.WriteDiaryRequest;
import a503.daracbang.domain.diary.dto.response.DiaryListResponse;
import a503.daracbang.domain.diary.dto.response.DiaryResponse;
import a503.daracbang.domain.diary.service.DeleteDiaryService;
import a503.daracbang.domain.diary.service.FindDiaryService;
import a503.daracbang.domain.diary.service.WriteDiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diaries")
public class DiaryController {

    private final WriteDiaryService writeDiaryService;
    private final FindDiaryService findDiaryService;
    private final DeleteDiaryService deleteDiaryService;

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("테스트 성공");
    }

    //다이어리 작성
    @PostMapping()
    public ResponseEntity<?> writeDiary(@RequestBody WriteDiaryRequest writeDiaryRequest){
//      Long memberId = MemberContextHolder.memberIdHolder.get();
        Long membedId = 2L; // 테스트용
        writeDiaryService.writeDiary(membedId, writeDiaryRequest);
        return ResponseEntity.ok("다이어리가 작성되었습니다.");
    }

    // 다이어리 삭제
    @DeleteMapping("/{diaryId}")
    public ResponseEntity<?> deleteDiary(@PathVariable Long diaryId){
        //    Long memberId = MemberContextHolder.memberIdHolder.get();
        Long memberId = 2L; // 테스트용
        deleteDiaryService.deleteDiary(memberId, diaryId);
        return ResponseEntity.ok("다이어리가 삭제되었습니다.");
    }

//    // 다이어리 상세 조회
//    @GetMapping("/{diaryId}")
//    public ResponseEntity<?> getDiary(@PathVariable Long diaryId){
//        DiaryResponse diaryResponse = findDiaryService.getDiary(diaryId);
//        return ResponseEntity.ok(diaryResponse);
//    }
//
//    // 전체 다이어리 조회
//    @GetMapping("/list/{memberId}")
//    public ResponseEntity<?> getDiaryList(@PathVariable Long memberId){
//        DiaryListResponse diaryListResponse = findDiaryService.getDiaryList(memberId);
//        return ResponseEntity.ok(diaryListResponse);
//    }
//
//    // 무드 트래커 조회
//    @GetMapping("/mood-tracker/{memberId}")
//    public ResponseEntity<?> getMoodTracker(@PathVariable Long memberId, @RequestParam("year") int year, @RequestParam("month") int month){
//        DiaryListResponse diaryListResponse = findDiaryService.getMoodTracker(memberId, month, year);
//        return ResponseEntity.ok(diaryListResponse);
//    }
}
