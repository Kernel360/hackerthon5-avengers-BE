package com.hackerthon5.avengers_BE.member.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponse {

    private Long memberId;
    private String memberName;
    private String nickName;
    private String email;


}
