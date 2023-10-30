package a503.daracbang.domain.guestbook.entity;

import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Guestbook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = false, length = 200)
    private String content;

    public Guestbook(Member member, String content) {
        this.member = member;
        this.content = content;
    }

    public boolean isWriter(long memberId) {
        if (this.member.getId() == memberId) {
            return true;
        }
        return false;
    }
}
