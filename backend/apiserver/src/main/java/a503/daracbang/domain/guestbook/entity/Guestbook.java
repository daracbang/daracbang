package a503.daracbang.domain.guestbook.entity;

import a503.daracbang.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false, length = 200)
    private String content;

    public Guestbook(Long memberId, String content) {
        this.memberId = memberId;
        this.content = content;
    }

    public boolean isWriter(long memberId) {
        if (this.memberId == memberId) {
            return true;
        }
        return false;
    }
}
