package a503.daracbang.domain.guestbook.repository;

import a503.daracbang.domain.guestbook.dto.response.GuestbookResponse;
import a503.daracbang.domain.guestbook.entity.Guestbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {

    @Query("SELECT new a503.daracbang.domain.guestbook.dto.response.GuestbookResponse(m.id, m.nickname, m.profileImage, gb.content) " +
        "FROM Guestbook gb " +
        "LEFT JOIN gb.member m ON gb.member.id = m.id " +
        "WHERE gb.member.id = :memberId")
    Page<GuestbookResponse> findAllGuestbookByPaging(@Param("memberId") long memberId,
                                                     Pageable pageable);
}
