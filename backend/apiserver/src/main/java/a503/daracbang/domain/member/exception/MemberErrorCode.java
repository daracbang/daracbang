package a503.daracbang.domain.member.exception;

import a503.daracbang.global.exception.ErrorCode;

public enum MemberErrorCode implements ErrorCode {
    MEMBER_NOT_FOUND("MEMBER_001","해당 회원을 찾을 수 없습니다.",404),
    DUPLICATE_MEMBER_LOGIN_ID("MEMBER_002", "중복된 아이디입니다.", 409),
    DUPLICATE_MEMBER_NICKNAME("MEMBER_003", "중복된 닉네임입니다.", 409),
    INCORRECT_PASSWORD("MEMBER_004", "비밀번호가 올바르지 않습니다", 400)
    ;

    private String errorCode;
    private String message;
    private int statusCode;

    MemberErrorCode(String errorCode, String message, int statusCode) {
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
