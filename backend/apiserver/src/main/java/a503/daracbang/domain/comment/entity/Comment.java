package a503.daracbang.domain.comment.entity;

import a503.daracbang.domain.diary.dto.response.SentimentResponse;
import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.entity.Sentiment;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @Column(nullable = false)
    private String content;

    @Embedded
    private Sentiment sentiment;

    @Builder
    public Comment(Member member, Diary diary, String content){
        this.member = member;
        this.diary = diary;
        this.content = content;
    }

    public void addSentiment(SentimentResponse response){
        this.sentiment = new Sentiment(response);
    }
}
