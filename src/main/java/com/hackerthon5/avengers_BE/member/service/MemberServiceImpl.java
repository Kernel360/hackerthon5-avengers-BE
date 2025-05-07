package com.hackerthon5.avengers_BE.member.service;

import com.hackerthon5.avengers_BE.member.DTO.MemberRequest;
import com.hackerthon5.avengers_BE.member.DTO.MemberResponse;
import com.hackerthon5.avengers_BE.member.domain.Member;
import com.hackerthon5.avengers_BE.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String save(MemberRequest request) {

        Optional<Member> exitsmember = memberRepository.findByEmail(request.getEmail());

        if (exitsmember.isPresent()) {
            if(exitsmember.get().getEmail().equals(request.getEmail())) {
                return "이미 존재하는 이메일입니다.";
            }else if(exitsmember.get().getNickname().equals(request.getNickname())) {
                return "이미 존재하는 닉네임입니다.";
            }
        }

        Member newMember = Member.builder()
                .memberName(request.getMemberName())
                .nickname(request.getNickname())
                .email(request.getEmail())
               // .password(passwordEncoder.encode(request.getPassword()))
                .password(request.getPassword())
                .registeredAt(LocalDateTime.now())
                .build();

        Member saveMember = memberRepository.save(newMember);

        return "회원가입을 축하드립니다. 로그인해주세요.";
    }

    @Override
    public ResponseEntity<MemberResponse> login(MemberRequest request) {



        return null;
    }
}
