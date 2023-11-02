package a503.daracbang.domain.diary.exception;

import a503.daracbang.global.exception.ErrorCode;
import jakarta.validation.constraints.NotBlank;

public enum DiaryErrorCode implements ErrorCode {

    NOTFOUND_DIARY("DIARY_001","해당 다이어리를 찾을 수 없습니다.",404),
    ALREADYWRITTEN_DIARY("DIARY_002","오늘은 이미 다이어리를 작성하셨습니다.",409),
    NOTWRITER_DIARY("DIARY_003","다이어리를 삭제할 권한이 없습니다.",403),
    SCOPENOTEXISTS_DIARY("DIARY_004","공개범위 설정이 잘못되었습니다.",400),
    NOTBLANK_DIARY("DIARY_005","다이어리 내용을 입력해주세요.",400),
    OUTOFRANGE_DIARY("DIARY_006","다이어리는 최소 50자, 최대 1000자 입력할 수 있습니다.",400);

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
