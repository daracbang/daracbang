package a503.daracbang.domain.diary.dto.request;


import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.entity.Scope;
import a503.daracbang.domain.member.entity.Member;
import lombok.Builder;

public class WriteDiaryRequest {
    private Member member;
    private String content;
    private Scope scope;

}
