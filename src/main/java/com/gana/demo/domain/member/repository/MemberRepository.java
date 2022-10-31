package com.gana.demo.domain.member.repository;

import com.gana.demo.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * {@link Member}를 데이터베이스에서 가져오는 Proxy 클래스
 * @see Member
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    /**
     * 사용자 인증때 사용되는 매소드
     * @param username 사용자 아이디
     * @return {@link Member}
     */
    Optional<Member> findByUsername(String username);

    /**
     * 사용자 아이디 중복검사때 사용되는 메소드
     * @param username 중복 검사가 필요한 아이디
     * @return 아이디 중복 여부
     */
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
