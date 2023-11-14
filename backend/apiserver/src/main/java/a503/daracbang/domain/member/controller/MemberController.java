package a503.daracbang.domain.member.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import a503.daracbang.domain.member.dto.request.IntroduceRequest;
import a503.daracbang.domain.member.dto.request.LoginMemberRequest;
import a503.daracbang.domain.member.dto.response.LoginMemberResponse;
import a503.daracbang.domain.member.dto.request.SignUpMemberRequest;
import a503.daracbang.domain.member.service.CheckDuplicateMemberInfoService;
import a503.daracbang.domain.member.service.GetMemberInfoService;
import a503.daracbang.domain.member.service.LoginMemberService;
import a503.daracbang.domain.member.service.SignUpMemberService;
import a503.daracbang.domain.member.service.UpdateMemberService;
import a503.daracbang.domain.member.util.MemberContextHolder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final CheckDuplicateMemberInfoService checkDuplicateMemberInfoService;
    private final SignUpMemberService signUpMemberService;
    private final LoginMemberService loginMemberService;
    private final UpdateMemberService updateMemberService;
    private final GetMemberInfoService getMemberInfoService;

    @PostMapping
    public ResponseEntity<?> signUp(@ModelAttribute @Valid SignUpMemberRequest signUpMemberRequest) {
        signUpMemberService.signUp(signUpMemberRequest);
        return ResponseEntity.status(201).body("회원가입이 완료되었습니다.");
    }

    @GetMapping("/login-id/{loginId}")
    public ResponseEntity<?> checkDuplicateLoginId(@PathVariable String loginId) {
        checkDuplicateMemberInfoService.checkDuplicateLoginId(loginId);
        return ResponseEntity.status(200).body("사용 가능한 ID입니다.");
    }

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<?> checkDuplicateNickname(@PathVariable String nickname) {
        checkDuplicateMemberInfoService.checkDuplicateNickname(nickname);
        return ResponseEntity.status(200).body("사용 가능한 닉네임입니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginMemberRequest loginMemberRequest) {
        String jwt = loginMemberService.login(loginMemberRequest.getLoginId(), loginMemberRequest.getPassword());
        return ResponseEntity.ok(new LoginMemberResponse(jwt));
    }

    @PutMapping("/introduce")
    public ResponseEntity<?> updateIntroduce(@RequestBody IntroduceRequest introduceRequest) {
        Long id = MemberContextHolder.memberIdHolder.get();
        updateMemberService.updateIntroduce(id, introduceRequest.getIntroduce());

        return ResponseEntity.status(200).body("소개글이 업데이트 되었습니다.");
    }

    @GetMapping("/info")
    public ResponseEntity<?> getMemberInfo() {
        Long id = MemberContextHolder.memberIdHolder.get();
        return ResponseEntity.ok(getMemberInfoService.getMemberInfo(id));
    }

    @GetMapping("/{nickname}/info")
    public ResponseEntity<?> getMemberId(@PathVariable String nickname) {
        return ResponseEntity.ok(getMemberInfoService.getMemberId(nickname));
    }
}
