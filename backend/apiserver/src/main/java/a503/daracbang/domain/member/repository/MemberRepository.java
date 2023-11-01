package a503.daracbang.domain.member.repository;

import java.util.Optional;

import a503.daracbang.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

	boolean existsByLoginId(String loginId);
	boolean existsByNickname(String nickname);

	Optional<Member> findByLoginId(String loginId);
}
