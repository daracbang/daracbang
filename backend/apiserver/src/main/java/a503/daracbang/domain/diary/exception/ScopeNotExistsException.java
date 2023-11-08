package a503.daracbang.domain.diary.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class ScopeNotExistsException extends CustomException {
    public ScopeNotExistsException() {
        super(DiaryErrorCode.SCOPENOTEXISTS_DIARY);
    }
}