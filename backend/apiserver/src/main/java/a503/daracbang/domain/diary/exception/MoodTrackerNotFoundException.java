package a503.daracbang.domain.diary.exception;

import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;

public class MoodTrackerNotFoundException extends CustomException {
    public MoodTrackerNotFoundException(ErrorCode errorCode){super(errorCode);}
}
