package a503.daracbang.domain.guestbook.entity;

import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Member owner;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member writer;

    @Column(nullable = false, length = 200)
    private String content;

    public Guestbook(Member owner, Member writer, String content) {
        this.owner = owner;
        this.writer = writer;
        this.content = content;
    }

    public boolean isWriter(long memberId) {
        if (this.writer.getId() == memberId) {
            return true;
        }
        return false;
    }
}
