package a503.daracbang.domain.member.dto.request.valid;

import a503.daracbang.domain.member.exception.MemberErrorCode;
import a503.daracbang.global.exception.CustomException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class AllowedContentTypeValidator implements  ConstraintValidator<AllowedContentType, MultipartFile> {

    private String[] allowedTypes;
    private String[] allowedExtensions;

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        if (multipartFile == null || multipartFile.isEmpty() || multipartFile.getSize() == 0) {
            throw new CustomException(MemberErrorCode.INVALID_PROFILE_IMAGE);
        }
        if(!(isValidExtension(multipartFile) && isValidContentType(multipartFile))){
            throw new CustomException(MemberErrorCode.INVALID_PROFILE_IMAGE);
        }
        return true;
    }

    @Override
    public void initialize(AllowedContentType constraintAnnotation) {
        allowedExtensions = constraintAnnotation.allowedExtensions();
        allowedTypes = constraintAnnotation.allowedTypes();
    }

    private boolean isValidExtension(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFilename)
                                          .toLowerCase();
        return Arrays.asList(allowedExtensions)
                     .contains(fileExtension);
    }

    private boolean isValidContentType(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        return Arrays.asList(allowedTypes)
                     .contains(contentType);
    }
}
