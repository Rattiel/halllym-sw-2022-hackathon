package com.gana.demo.domain.member.dto;

import com.gana.demo.domain.member.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
public class MemberCreateParameter {
    private String nickname;

    private String username;

    private String password;

    private String passwordConfirm;

    private String email;

    private Role role;

    public static MemberCreateParameter from(MemberRegisterForm form) {
        return MemberCreateParameter.builder()
                    .nickname(form.getNickname())
                    .username(form.getUsername())
                    .password(form.getPassword())
                    .passwordConfirm(form.getPasswordConfirm())
                    .email(form.getEmail())
                    .role(Role.USER)
                .build();
    }

    public static MemberCreateParameter from(MemberRegisterForm form, Role role) {
        return MemberCreateParameter.builder()
                    .nickname(form.getNickname())
                    .username(form.getUsername())
                    .password(form.getPassword())
                    .passwordConfirm(form.getPasswordConfirm())
                    .email(form.getEmail())
                    .role(role)
                .build();
    }
}
