package a503.daracbang.domain.comment.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class CommentNotFoundException extends CustomException {
    public CommentNotFoundException(){
        super(CommentErrorCode.NOTFOUND_COMMENT);
    }
}
