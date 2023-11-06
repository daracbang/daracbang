package a503.daracbang.domain.comment.repository;

import a503.daracbang.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepositoty extends JpaRepository<Comment, Long> {
    List<Comment> findAllByDiaryId(Long diaryId);
}
