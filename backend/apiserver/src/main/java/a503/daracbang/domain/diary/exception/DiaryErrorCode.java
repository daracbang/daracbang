package a503.daracbang.domain.diary.exception;

import a503.daracbang.global.exception.ErrorCode;

public enum DiaryErrorCode implements ErrorCode {

    NOTFOUND_DIARY("DIARY_001","해당 다이어리를 찾을 수 없습니다.",404),
    ALREADYWRITTEN_DIARY("DIARY_002","오늘은 이미 다이어리를 작성하셨습니다.",409);

    private String errorCode;
    private String message;
    private int statusCode;

    DiaryErrorCode(String errorCode, String message, int statusCode) {
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
