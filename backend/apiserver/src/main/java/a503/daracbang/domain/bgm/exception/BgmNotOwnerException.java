package a503.daracbang.domain.bgm.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class BgmNotOwnerException extends CustomException {
    public BgmNotOwnerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
