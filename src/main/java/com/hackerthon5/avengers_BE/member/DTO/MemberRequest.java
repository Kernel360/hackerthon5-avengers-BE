package com.hackerthon5.avengers_BE.member.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberRequest {

    @NotBlank
    private String memberName;

    @NotBlank
    private String nickname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
