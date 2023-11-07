package a503.daracbang.domain.diary.dto.request;


import a503.daracbang.domain.diary.controller.DiaryLengthCheck;
import a503.daracbang.domain.diary.controller.ValidEnum;
import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.entity.Scope;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WriteDiaryRequest {

    @DiaryLengthCheck
    private String content;

    @ValidEnum(enumClass = Scope.class)
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
