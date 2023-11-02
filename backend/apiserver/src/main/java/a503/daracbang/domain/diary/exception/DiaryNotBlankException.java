package a503.daracbang.domain.diary.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class DiaryNotBlankException extends CustomException {
    public DiaryNotBlankException(ErrorCode errorCode) {super(errorCode);}
}
