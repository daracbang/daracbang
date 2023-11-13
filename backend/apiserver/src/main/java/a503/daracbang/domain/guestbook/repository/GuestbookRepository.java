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

    @Query("SELECT new a503.daracbang.domain.guestbook.dto.response.GuestbookResponse(g.id, m.id, m.nickname, m.profileImage, g.content, g.createdAt) " +
          "FROM Guestbook g " +
          "JOIN g.member m " +
          "WHERE m.id = :memberId " +
          "ORDER BY g.id DESC")
    List<GuestbookResponse> findGuestBookFirstPage(@Param("memberId") long memberId,
                                                     Pageable pageable);

    @Query("SELECT new a503.daracbang.domain.guestbook.dto.response.GuestbookResponse(g.id, m.id, m.nickname, m.profileImage, g.content, g.createdAt) " +
          "FROM Guestbook g " +
          "JOIN g.member m " +
          "WHERE m.id = :memberId " +
          "AND g.id < :lastId " +
          "ORDER BY g.id DESC")
    List<GuestbookResponse> findGuestBookNextPage(@Param("memberId") long memberId,
                                                     @Param("lastId") long lastId,
                                                     Pageable pageable);
}
