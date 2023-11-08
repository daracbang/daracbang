package a503.daracbang.domain.comment.exception;

import a503.daracbang.global.exception.ErrorCode;

public enum CommentErrorCode implements ErrorCode {
    NOTFOUND_COMMENT("COMMENT_001","해당 댓글을 찾을 수 없습니다.",404),
    NOTWRITER_COMMENT("COMMENT_001","댓글을 삭제할 권한이 없습니다.",403);

    private String errorCode;
    private String message;
    private int statusCode;

    CommentErrorCode(String errorCode, String message, int statusCode) {
        this.errorCode = errorCode;
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }


}
