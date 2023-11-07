package a503.daracbang.domain.diary.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class DiaryNotWriterException extends CustomException {
    public DiaryNotWriterException(ErrorCode errorCode) {
        super(errorCode);
    }
}