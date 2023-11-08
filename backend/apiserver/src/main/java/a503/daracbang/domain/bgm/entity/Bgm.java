package a503.daracbang.domain.bgm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Bgm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String bgmName;

    private String videoId;

    private String url;

    @Builder
    public Bgm(Long memberId, String bgmName, String url) {
        this.memberId = memberId;
        this.bgmName = bgmName;
        this.url = url;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
