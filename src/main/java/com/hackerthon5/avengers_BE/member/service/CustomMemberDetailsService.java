package com.hackerthon5.avengers_BE.member.service;

import com.hackerthon5.avengers_BE.member.domain.Member;
import com.hackerthon5.avengers_BE.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("유저정보가 없습니다."));

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .build();
    }
}