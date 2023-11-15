package a503.daracbang.domain.bgm.repository;

import a503.daracbang.domain.bgm.dto.response.MyBgmResponse;
import a503.daracbang.domain.bgm.entity.Bgm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BgmRepository extends JpaRepository<Bgm, Long> {

    @Query("select new a503.daracbang.domain.bgm.dto.response.MyBgmResponse(b.id, b.bgmName, b.videoId) " +
           "from Bgm b " +
           "where b.member.id = :memberId")
    List<MyBgmResponse> findAllByMemberId(@Param("memberId") long memberId);
}
