package a503.daracbang.domain.member.repository;

import a503.daracbang.domain.neighbor.dto.response.MemberSearchResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import a503.daracbang.domain.member.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,Long> {

	boolean existsByLoginId(String loginId);
	boolean existsByNickname(String nickname);

	Optional<Member> findByLoginId(String loginId);
	Optional<Member> findByNickname(String nickname);
 	List<Member> findAllByNicknameContaining(String nickname);

	@Query("SELECT new a503.daracbang.domain.neighbor.dto.response.MemberSearchResponse(m.id, m.nickname, m.profileImage, " +
			"(CASE WHEN n IS NOT NULL AND n.isCon = TRUE THEN TRUE ELSE FALSE END), " +
			"(CASE WHEN n IS NOT NULL AND n.isCon = FALSE THEN TRUE ELSE FALSE END)) " +
			"FROM Member m " +
			"LEFT JOIN Neighbor n ON (m.id = n.requester.id OR m.id = n.accepter.id) AND :memberId IN (n.requester.id, n.accepter.id) " +
			"WHERE m.nickname LIKE %:nickname% ")
	List<MemberSearchResponse> searchMembers(@Param("nickname") String nickname, @Param("memberId") Long memberId);
}
