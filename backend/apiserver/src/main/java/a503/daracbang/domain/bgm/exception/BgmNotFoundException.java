package a503.daracbang.domain.bgm.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class BgmNotFoundException extends CustomException {
    public BgmNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
