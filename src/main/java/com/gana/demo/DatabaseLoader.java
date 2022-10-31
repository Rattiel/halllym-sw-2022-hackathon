package com.gana.demo;

import com.gana.demo.domain.member.Member;
import com.gana.demo.domain.member.Role;
import com.gana.demo.domain.member.dto.MemberCreateParameter;
import com.gana.demo.domain.member.dto.MemberRegisterForm;
import com.gana.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseLoader implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "1234";
    private static final String ADMIN_NICKNAME = "관리자";
    private static final String ADMIN_EMAIL = "admin@demo.com";

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        MemberRegisterForm adminMemberRegisterForm = MemberRegisterForm.builder()
                .username(ADMIN_USERNAME)
                .password(ADMIN_PASSWORD)
                .passwordConfirm(ADMIN_PASSWORD)
                .nickname(ADMIN_NICKNAME)
                .email(ADMIN_EMAIL)
                .build();

        memberRepository.save(Member.of(MemberCreateParameter.from(adminMemberRegisterForm, Role.ADMIN), passwordEncoder));

        log.info("관리자 계정 (아이디 : {}, 비밀번호 : {})", ADMIN_USERNAME, ADMIN_PASSWORD);
    }
}
