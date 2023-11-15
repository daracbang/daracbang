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
		+ "JOIN Neighbor n2 ON n1.accepter = n2.requester "
		+ "WHERE n1.requester.id = :memberId AND n2.accepter.id = :memberId AND n1.isCon = true AND n2.isCon = true")
	@EntityGraph(attributePaths = {"requester", "accepter"})
	List<Neighbor> findAllMyNeighbor(@Param("memberId") Long memberId);
	@EntityGraph(attributePaths = {"requester", "accepter"})
	List<Neighbor> findAllByIsConFalseAndRequester(Member requester);
	Neighbor findByRequesterAndAccepter(Member requester, Member accepter);
	boolean existsByRequesterAndAccepter(Member requester, Member accepter);
	void deleteByRequesterAndAccepter(Member requester, Member accepter);
	@Query("SELECT n.isCon FROM Neighbor n WHERE n.accepter.id = :accepterId AND n.requester.id= :requesterId")
	Optional<Boolean> findIsConByAccepterIdAndRequesterId(@Param("accepterId") Long accepterId, @Param("requesterId") Long requesterId);
}
