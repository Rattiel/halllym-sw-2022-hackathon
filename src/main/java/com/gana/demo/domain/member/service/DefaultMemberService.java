package com.gana.demo.domain.member.service;

import com.gana.demo.domain.member.Member;
import com.gana.demo.domain.member.dto.MemberCreateParameter;
import com.gana.demo.domain.member.dto.MemberDetails;
import com.gana.demo.domain.member.exception.MemberEmailExistsException;
import com.gana.demo.domain.member.exception.MemberPasswordIncorrectException;
import com.gana.demo.domain.member.exception.MemberUsernameExistsException;
import com.gana.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class DefaultMemberService implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );

        return MemberDetails.from(member);
    }

    @Transactional
    @Override
    public void register(MemberCreateParameter parameter) throws MemberPasswordIncorrectException, MemberUsernameExistsException, MemberEmailExistsException {
        check(
                parameter.getUsername(),
                parameter.getEmail(),
                parameter.getPassword(),
                parameter.getPasswordConfirm()
        );

        memberRepository.save(Member.of(parameter, passwordEncoder));
    }

    private void check(String username, String email, String password, String passwordConfirm) {
        usernameCheck(username);
        emailCheck(email);
        passwordCheck(password, passwordConfirm);
    }

    private void usernameCheck(String username) {
        if (memberRepository.existsByUsername(username)) {
            throw new MemberUsernameExistsException();
        }
    }

    private void emailCheck(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new MemberEmailExistsException();
        }
    }

    private void passwordCheck(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            throw new MemberPasswordIncorrectException();
        }
    }
}
