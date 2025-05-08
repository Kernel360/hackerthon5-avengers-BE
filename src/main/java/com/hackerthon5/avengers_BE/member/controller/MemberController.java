package com.hackerthon5.avengers_BE.member.controller;

import com.hackerthon5.avengers_BE.member.DTO.SignupRequest;
import com.hackerthon5.avengers_BE.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequest userDto) {
        return new ResponseEntity<>(memberService.signup(userDto), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> getMyUserInfo() {
        return ResponseEntity.ok("user");
    }

    @PostMapping("/user/{username}")
    public ResponseEntity<String> getUserInfo(@PathVariable("username") String username) {
        return ResponseEntity.ok("admin");
    }

}