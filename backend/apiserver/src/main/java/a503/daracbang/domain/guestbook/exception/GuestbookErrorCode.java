package a503.daracbang.domain.guestbook.exception;

import a503.daracbang.global.exception.ErrorCode;

public enum GuestbookErrorCode implements ErrorCode {
    NOTWRITER_GUESTBOOK("GUESTBOOK_002","방명록 작성자가 아닙니다.",403),
    NOTFOUND_GUESTBOOK("GUESTBOOK_001","해당 방명록을 찾을 수 없습니다.",404);

    private String errorCode;
    private String message;
    private int statusCode;

    GuestbookErrorCode(String errorCode, String message, int statusCode) {
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
