package a503.daracbang.domain.neighbor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.neighbor.entity.Neighbor;

public interface NeighborRepository extends JpaRepository<Neighbor, Long> {
	@Query("select n1 from Neighbor n1 join Neighbor n2 on n1.accepter = n2.requester "
		+ "where n1.requester.id = :memberId and n2.accepter.id = :memberId and n1.isNeighbor = true and n2.isNeighbor = true")
	@EntityGraph(attributePaths = {"requester", "accepter"})
	List<Neighbor> findAllMyNeighbor(@Param("memberId") Long memberId);

	Neighbor findByRequesterAndAccepter(Member requester, Member accepter);
	boolean existsByRequesterAndAccepter(Member requester, Member accepter);

	void deleteByRequesterAndAccepter(Member requester, Member accepter);

}
