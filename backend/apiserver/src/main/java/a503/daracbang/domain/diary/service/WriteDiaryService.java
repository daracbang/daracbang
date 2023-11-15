package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.dto.request.WriteDiaryRequest;
import a503.daracbang.domain.diary.dto.response.SentimentResponse;
import a503.daracbang.domain.diary.entity.Diary;
import a503.daracbang.domain.diary.exception.DiaryAlreadyWrittenException;
import a503.daracbang.domain.diary.exception.DiaryErrorCode;
import a503.daracbang.domain.diary.repository.DiaryRepository;
import a503.daracbang.domain.member.exception.MemberErrorCode;
import a503.daracbang.domain.member.repository.MemberRepository;
import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.exception.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class WriteDiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryTimePolicy diaryTimePolicy;
    private final MemberRepository memberRepository;
    private final AnalysisSentimentService analysisSentimentService;

    public void writeDiary(Long memberId, WriteDiaryRequest writeDiaryRequest) throws JsonProcessingException {
        if(!diaryTimePolicy.verify(memberId, LocalDate.now()))
            throw new DiaryAlreadyWrittenException();
        Diary diary = writeDiaryRequest.toEntity(memberRepository.findById(memberId)
                .orElseThrow(()->new CustomException(MemberErrorCode.MEMBER_NOT_FOUND)));
        diaryRepository.save(diary);
        SentimentResponse sentimentResponse = analysisSentimentService.requestCLOVA(diary.getContent());
        diary.addSentiment(sentimentResponse);
        diaryRepository.save(diary);
    }
}
