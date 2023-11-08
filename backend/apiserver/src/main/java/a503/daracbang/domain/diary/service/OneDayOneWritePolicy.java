package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
public class OneDayOneWritePolicy implements DiaryTimePolicy{

    private final DiaryRepository diaryRepository;

    @Override
    public boolean verify(Long memberId, LocalDate today) {
        return diaryRepository.findAllByMemberIdAndCreatedAtIsAfter(memberId, today.atStartOfDay()).isEmpty();
    }

}
