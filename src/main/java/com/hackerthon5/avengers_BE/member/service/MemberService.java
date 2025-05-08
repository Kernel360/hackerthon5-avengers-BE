package com.hackerthon5.avengers_BE.member.service;

import com.hackerthon5.avengers_BE.member.DTO.LoginRequest;
import com.hackerthon5.avengers_BE.member.DTO.SignupRequest;
import com.hackerthon5.avengers_BE.member.DTO.TokenResponse;
import com.hackerthon5.avengers_BE.member.domain.Member;


public interface MemberService {

    // 회원가입
    String signup(SignupRequest request);

}
