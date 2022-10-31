package com.gana.demo.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Builder
@Setter
@Getter
public class MemberRegisterForm {
    @NotEmpty(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "올바른 닉네임 형식이 아닙니다.")
    private String nickname;

    @NotEmpty(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "올바른 아이디 형식이 아닙니다.")
    private String username;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,20}$", message = "올바른 비밀번호 형식이 아닙니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인을 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,20}$", message = "올바른 비밀번호 확인 형식이 아닙니다.")
    private String passwordConfirm;

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 형식 아닙니다.")
    private String email;
}

