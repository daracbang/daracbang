package a503.daracbang.domain.bgm.entity;

import a503.daracbang.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String bgmName;

    private String url;

    public boolean isOwner(long memberId) {
        if(this.member.getId() != memberId) {
            return false;
        }
        return true;
    }

    public Bgm(Member member, String bgmName, String url) {
        this.member = member;
        this.bgmName = bgmName;
        this.url = url;
    }
}
