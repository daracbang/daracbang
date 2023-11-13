package a503.daracbang.global.exception;

public enum GlobalErrorCode implements ErrorCode{
    SERVER_ERROR("GLOBAL_001","알수 없는 에러",500),
    IMAGE_UPLOAD_FAIL("GLOBAL_002","s3 image 업로드 실패",500);
    private String errorCode;
    private String message;
    private int statusCode;

    GlobalErrorCode(String errorCode, String message, int statusCode) {
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
