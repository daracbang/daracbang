package a503.daracbang.domain.diary.service;

import a503.daracbang.domain.diary.dto.response.SentimentResponse;
import a503.daracbang.domain.diary.entity.Diary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class AnalysisSentimentService {

    @Value("${clova.id}")
    private String clovaId;

    @Value("${clova.secret}")
    private String clovaSecret;

    public SentimentResponse requestCLOVA(String content) throws JsonProcessingException {

        String url = "https://naveropenapi.apigw.ntruss.com/sentiment-analysis/v1/analyze";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-NCP-APIGW-API-KEY-ID", clovaId);
        httpHeaders.set("X-NCP-APIGW-API-KEY", clovaSecret);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("content", content);

        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        return objectMapper.readValue(response.getBody(), SentimentResponse.class);
    }
}
