package com.hackerthon5.avengers_BE.member.service;

import com.hackerthon5.avengers_BE.member.DTO.MemberRequest;
import com.hackerthon5.avengers_BE.member.DTO.MemberResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    // 회원가입
    String save(MemberRequest request);

    //로그인
    ResponseEntity<MemberResponse> login(MemberRequest request);
}
