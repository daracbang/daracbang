package a503.daracbang.global.aws;

import a503.daracbang.config.AwsS3Property;
import a503.daracbang.domain.member.service.ProfileImageUploader;
import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.GlobalErrorCode;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Uploader implements ProfileImageUploader {

    private final AmazonS3 amazonS3;
    private final AwsS3Property awsS3Property;

    @Override
    public String upload(MultipartFile file) {
        String PROFILE_PATH = "profile/";
        return uploadFile(PROFILE_PATH, file);
    }

    public String uploadFile(String directoryPath, MultipartFile multipartFile) {
        String AWSURL = awsS3Property.getAwsUrl();
        String fileName = createFileName(directoryPath, multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        try {
            InputStream inputStream = multipartFile.getInputStream();
            amazonS3.putObject(
                    new PutObjectRequest(awsS3Property.getBucket(), fileName, inputStream,
                            objectMetadata));

            return AWSURL + fileName;
        } catch (Exception e) {
            log.error("업로드 실패");
            throw new CustomException(GlobalErrorCode.IMAGE_UPLOAD_FAIL);
        }
    }

    private String createFileName(String directoryPath, String fileName) {
        String convertFilename = Base64.getUrlEncoder().encodeToString(fileName.getBytes());
        return directoryPath + UUID.randomUUID().toString().concat(convertFilename);
    }
}
