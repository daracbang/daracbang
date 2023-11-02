package a503.daracbang.domain.diary.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class DiaryBlankException extends CustomException {
    public DiaryBlankException(ErrorCode errorCode){
        super(errorCode);
    }
}
