package a503.daracbang.domain.diary.controller;

import a503.daracbang.domain.diary.exception.DiaryBlankException;
import a503.daracbang.domain.diary.exception.DiaryErrorCode;
import a503.daracbang.domain.diary.exception.DiaryLengthException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DiaryLengthCheckValidator implements ConstraintValidator<DiaryLengthCheck,String> {

    @Override
    public boolean isValid(String content, ConstraintValidatorContext context) {
        if(content==null||content.trim().isEmpty())
            throw new DiaryBlankException(DiaryErrorCode.BLANK_DIARY);
        if(content.length()<50 || content.length()>1000)
            throw new DiaryLengthException(DiaryErrorCode.OUTOFLENGTH_DIARY);
        return true;
    }
}
