package a503.daracbang.domain.bgm.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class YoutubeErrorException extends CustomException  {
    public YoutubeErrorException(ErrorCode errorCode) {
        super(errorCode);
    }
}
