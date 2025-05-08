package com.hackerthon5.avengers_BE.member.controller;


import com.hackerthon5.avengers_BE.member.DTO.MemberRequest;
import com.hackerthon5.avengers_BE.member.DTO.MemberResponse;
import com.hackerthon5.avengers_BE.member.service.MemberService;
import com.hackerthon5.avengers_BE.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signup (@RequestBody MemberRequest request) {
            String result = memberServiceImpl.save(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login (@RequestBody MemberRequest request) {
        return memberServiceImpl.login(request);
    }
}
