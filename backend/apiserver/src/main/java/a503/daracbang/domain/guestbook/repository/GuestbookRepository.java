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

    @Query("SELECT new a503.daracbang.domain.guestbook.dto.response.GuestbookResponse(g.id, m.nickname, m.profileImage, gb.content) " +
        "FROM Guestbook gb " +
        "LEFT JOIN gb.member m ON gb.member.id = m.id " +
        "WHERE gb.member.id = :memberId " +
        "ORDER BY id DESC")
    List<GuestbookResponse> findGuestBookFirstPage(@Param("memberId") long memberId,
                                                     Pageable pageable);

    @Query("SELECT new a503.daracbang.domain.guestbook.dto.response.GuestbookResponse(g.id, m.nickname, m.profileImage, gb.content) " +
        "FROM Guestbook gb " +
        "LEFT JOIN gb.member m ON gb.member.id = m.id " +
        "WHERE gb.member.id = :memberId " +
        "AND gb.id < :lastId " +
        "ORDER BY id DESC")
    List<GuestbookResponse> findGuestBookNextPage(@Param("memberId") long memberId,
                                                     @Param("lastId") long lastId,
                                                     Pageable pageable);
}
