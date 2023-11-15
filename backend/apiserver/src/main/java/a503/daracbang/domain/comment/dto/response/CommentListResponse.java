package a503.daracbang.domain.comment.dto.response;

import a503.daracbang.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CommentListResponse {

    private List<CommentResponse> commentList;

    public CommentListResponse(List<Comment> commentList){
        this.commentList = commentList.stream().map(CommentResponse::new).collect(Collectors.toList());
    }
}
