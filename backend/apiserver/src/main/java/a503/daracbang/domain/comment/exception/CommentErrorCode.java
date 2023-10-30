package a503.daracbang.domain.comment.exception;

import a503.daracbang.global.exception.ErrorCode;

public enum CommentErrorCode implements ErrorCode {
    NOTFOUND_MEMBER("MEMBER_001","해당 회원을 찾을 수 없습니다.",404);

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
