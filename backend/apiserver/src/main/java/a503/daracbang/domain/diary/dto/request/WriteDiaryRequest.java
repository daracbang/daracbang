package a503.daracbang.domain.diary.dto.request;


import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.entity.Scope;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WriteDiaryRequest {

    @NotBlank
    @Size(min=50, max=1000)
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
