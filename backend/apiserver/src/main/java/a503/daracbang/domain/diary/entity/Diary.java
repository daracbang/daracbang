package a503.daracbang.domain.diary.entity;

import a503.daracbang.domain.comment.entity.Comment;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Scope scope; // 다이어리 공개 범위

    @Column(columnDefinition = "TEXT", length = 1000, nullable = false)
    private String content; // 내용

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    private List<Comment> comments =new ArrayList<>(); // 댓글

    @Embedded
    private Sentiment sentiment; // 다이어리 감정 분석

    @Builder
    public Diary(Long memberId, Scope scope, String content){
        this.scope = scope;
        this.content = content;
        this.memberId = memberId;
        this.sentiment = null;
    }

}
