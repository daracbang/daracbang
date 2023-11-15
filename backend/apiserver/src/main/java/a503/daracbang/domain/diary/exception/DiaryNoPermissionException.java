package a503.daracbang.domain.diary.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class DiaryNoPermissionException extends CustomException {
    public DiaryNoPermissionException(){
        super(DiaryErrorCode.NOPERMISSION_DIARY);
    }
}
