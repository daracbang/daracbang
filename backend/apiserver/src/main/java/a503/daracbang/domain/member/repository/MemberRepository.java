package a503.daracbang.domain.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import a503.daracbang.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {

	boolean existsByLoginId(String loginId);
	boolean existsByNickname(String nickname);

	Optional<Member> findByLoginId(String loginId);
	Optional<Member> findByNickname(String nickname);
 	List<Member> findAllByNicknameContaining(String nickname);

}
