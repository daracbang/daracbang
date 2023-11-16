package a503.daracbang.domain.neighbor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.neighbor.entity.Neighbor;

public interface NeighborRepository extends JpaRepository<Neighbor, Long> {
	@Query("SELECT n1 "
		+ "FROM Neighbor n1 "
		+ "WHERE (n1.requester.id = :memberId OR n1.accepter.id = :memberId) AND n1.isCon = true ")
	@EntityGraph(attributePaths = {"requester", "accepter"})
	List<Neighbor> findAllMyNeighbor(@Param("memberId") Long memberId);
	@EntityGraph(attributePaths = {"requester", "accepter"})
	List<Neighbor> findAllByIsConFalseAndAccepter(Member requester);
	@EntityGraph(attributePaths = {"requester", "accepter"})
	List<Neighbor> findAllByIsConFalseAndRequester(Member requester);
	Optional<Neighbor> findByRequesterAndAccepter(Member requester, Member accepter);
	boolean existsByRequesterAndAccepter(Member requester, Member accepter);
	void deleteByRequesterAndAccepter(Member requester, Member accepter);
	@Query("SELECT n.isCon FROM Neighbor n WHERE n.accepter.id = :accepterId AND n.requester.id= :requesterId")
	Optional<Boolean> findIsConByAccepterIdAndRequesterId(@Param("accepterId") Long accepterId, @Param("requesterId") Long requesterId);

	@Query("SELECT n "
            + "FROM Neighbor n "
            + "WHERE (n.requester.id = :memberId1 AND n.accepter.id = :memberId2 )"
			+ " OR (n.accepter.id = :memberId1 AND n.requester.id = :memberId2 ) ")
	@EntityGraph(attributePaths = {"requester", "accepter"})
	Optional<Neighbor> findByMemberIdPair(@Param("memberId1") Long memberId1, @Param("memberId2") Long memberId2);
}
