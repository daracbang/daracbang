package a503.daracbang.domain.member.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {


    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.status(200)
                .body("성공");
    }

}
