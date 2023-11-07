package a503.daracbang.global.response;

import a503.daracbang.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FailResponse {
    private String message;
    private String errorCode;

    public FailResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public FailResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.errorCode = errorCode.getErrorCode();
    }
}
