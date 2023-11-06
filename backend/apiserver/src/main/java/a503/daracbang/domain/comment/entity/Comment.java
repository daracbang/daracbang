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

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long diaryId;

    @Column(nullable = false)
    private String content;

    @Embedded
    private Sentiment sentiment;

    @Builder
    public Comment(Long memberId, Long diaryId, String content){
        this.memberId = memberId;
        this.diaryId = diaryId;
        this.content = content;
    }

    public void addSentiment(SentimentResponse response){
        this.sentiment = new Sentiment(response);
    }
}
