package a503.daracbang.domain.diary.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class DiaryAlreadyWrittenException extends CustomException {
    public DiaryAlreadyWrittenException(){
        super(DiaryErrorCode.ALREADYWRITTEN_DIARY);
    }
}
