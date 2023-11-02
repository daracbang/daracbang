package a503.daracbang.domain.diary.repository;

import a503.daracbang.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByMemberId(Long memberId);

    Optional<Diary> findById(Long id);

    List<Diary> findAllByMemberIdAndCreatedAtIsAfter(Long memberId, LocalDateTime today);
}
