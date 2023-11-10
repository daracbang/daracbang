package a503.daracbang.domain.comment.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class CommentNotWriterException extends CustomException {
    public CommentNotWriterException(){
        super(CommentErrorCode.NOTWRITER_COMMENT);
    }
}
