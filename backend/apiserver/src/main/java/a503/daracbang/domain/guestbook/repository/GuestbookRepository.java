package a503.daracbang.domain.guestbook.repository;

import a503.daracbang.domain.guestbook.dto.response.GuestbookResponse;
import a503.daracbang.domain.guestbook.entity.Guestbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {

    /**
     * Member 구현이 안 돼서 주석처리함
     */
//    @Query("SELECT new a503.daracbang.domain.guestbook.dto.response.GuestbookResponse(m.id, m.nickname, m.profileImage, gb.content) " +
//        "FROM Guestbook gb " +
//        "LEFT JOIN gb.member m ON gb.member.id = m.id " +
//        "WHERE gb.member.id = :memberId " +
//        "AND gb.id < :nextId ")
//    List<GuestbookResponse> findAllGuestbookByPaging(@Param("memberId") long memberId,
//                                                     @Param("nextId") long nextId,
//                                                     Pageable pageable);
}
