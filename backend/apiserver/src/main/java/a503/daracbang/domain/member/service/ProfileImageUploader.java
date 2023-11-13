package a503.daracbang.domain.member.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageUploader {

    String upload(MultipartFile file);
}
