package a503.daracbang.domain.diary.dto.request;


import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.entity.Scope;
import a503.daracbang.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WriteDiaryRequest {
    private String content;
    private Scope scope;

    @Builder
    public WriteDiaryRequest(String content, Scope scope){
        this.content = content;
        this.scope = scope;
    }

    public Diary toEntity(Long memberId){

        return Diary.builder()
                .scope(this.scope)
                .content(this.content)
                .memberId(memberId)
                .build();
    }

    public WriteDiaryRequest() {
    }

}
