package a503.daracbang.domain.guestbook.repository;

import a503.daracbang.domain.guestbook.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {
}
