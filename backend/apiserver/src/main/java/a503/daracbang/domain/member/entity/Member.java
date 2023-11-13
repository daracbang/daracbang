package a503.daracbang.domain.member.entity;

import java.util.ArrayList;
import java.util.List;

import a503.daracbang.domain.bgm.entity.Bgm;
import a503.daracbang.domain.comment.entity.Comment;
import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.guestbook.entity.Guestbook;
import a503.daracbang.domain.neighbor.entity.Neighbor;
import a503.daracbang.global.entity.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    private String nickname;

    private String profileImage; // s3

    private String introduce;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Diary> diaries = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Guestbook> guestbooks = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Bgm> bgms = new ArrayList<>();

    @OneToMany(mappedBy = "requester", cascade = CascadeType.ALL)
    private List<Neighbor> requesters = new ArrayList<>();

    @OneToMany(mappedBy = "accepter", cascade = CascadeType.ALL)
    private List<Neighbor> accepters = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    /* BGM 추가 예정 */

    @Builder
    public Member(String loginId, String password, String nickname, String profileImage) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.introduce = null;
    }

    public void updateIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
