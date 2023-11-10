package a503.daracbang.domain.diary.entity;

import a503.daracbang.domain.comment.entity.Comment;
import a503.daracbang.domain.diary.dto.response.SentimentResponse;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Diary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private Scope scope; // 다이어리 공개 범위

    @Column(columnDefinition = "TEXT", length = 1000, nullable = false)
    private String content; // 내용

    @Embedded
    private Sentiment sentiment; // 다이어리 감정 분석

    @Builder
    public Diary(Member member, Scope scope, String content){
        this.scope = scope;
        this.content = content;
        this.member = member;
        this.sentiment = null;
    }

    public void addSentiment(SentimentResponse response){
        this.sentiment = new Sentiment(response);
    }

    public boolean isOwner(Long requesterId){
        return this.member.getId().equals(requesterId);
    }

}
