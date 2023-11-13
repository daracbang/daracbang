package a503.daracbang.domain.bgm.exception;

import a503.daracbang.global.exception.ErrorCode;

public enum BgmErrorCode implements ErrorCode {

    NOT_OWNER_BGM("BGM_001","권한이 없습니다.",403),
    NOT_FOUND_BGM("BGM_002","존재하지 않는 BGM 입니다.",404),
    YOUTUBE_ERROR_BGM("BGM_003","유뷰브 API 오류입니다.",503);

    private String errorCode;
    private String message;
    private int statusCode;

    BgmErrorCode(String errorCode, String message, int statusCode) {
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
