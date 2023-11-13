package a503.daracbang.domain.diary.repository;

import a503.daracbang.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByMemberId(Long memberId);

    Optional<Diary> findById(Long id);

    List<Diary> findAllByMemberIdAndCreatedAtIsAfter(Long memberId, LocalDateTime today);

    @Query("SELECT d FROM Diary d WHERE d.member.id = :memberId AND FUNCTION('YEAR', d.createdAt) = :year AND FUNCTION('MONTH', d.createdAt) = :month")
    List<Diary> findByMemberIdAndCreatedAtYearAndMonth(@Param("memberId") Long memberId, @Param("year") int year, @Param("month") int month);

    @Query("SELECT d FROM Diary d WHERE d.member.id = :memberId ORDER BY d.createdAt DESC")
    List<Diary> findByMemberIdOrderByCreatedAt(@Param("memberId") Long memberId);
}
