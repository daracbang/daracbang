package a503.daracbang.domain.diary.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

public interface DiaryTimePolicy {
    boolean verify(Long memberId, LocalDate today);
}
