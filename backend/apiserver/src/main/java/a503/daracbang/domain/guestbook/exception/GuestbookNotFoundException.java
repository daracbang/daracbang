package a503.daracbang.domain.guestbook.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class GuestbookNotFoundException extends CustomException {
    public GuestbookNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
