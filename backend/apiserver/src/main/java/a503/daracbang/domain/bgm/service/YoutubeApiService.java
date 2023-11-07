package a503.daracbang.domain.bgm.service;

import a503.daracbang.domain.bgm.dto.response.BgmListResponse;
import a503.daracbang.domain.bgm.dto.response.BgmResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class YoutubeApiService {

    private final String KEY = "AIzaSyDhMN7faYAEwG2l-E2-dfAVOQR2HTdaeuE";
    private final String ITEMS = "items";
    private final String ITEM_ID = "id";
    private final String VIDEO_ID = "videoId";
    private final String SNIPPET = "snippet";
    private final String TITLE = "title";
    private int maxResults = 5;

    private final ObjectMapper mapper = new ObjectMapper();

    public BgmListResponse findMusic(String q) {
        String url = String.format("https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=%d&order=viewCount&q=%s&key=%s", maxResults, q, KEY);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            // 여기서 responseBody를 처리
            // Gson 라이브러리를 사용하여 JSON을 Java 객체로 변환
            JsonNode rootNode = mapper.readTree(responseBody);
            JsonNode itemsNode = rootNode.path(ITEMS);
            List<BgmResponse> bgms = new ArrayList<>();
            for (int i = 0; i < maxResults; i++) {
                JsonNode itemNode = itemsNode.get(i);
                String id = itemNode.path(ITEM_ID).path(VIDEO_ID).asText();
                String title = itemNode.path(SNIPPET).path(TITLE).asText();
                bgms.add(new BgmResponse(id, title));
            }

            // items의 첫 번째 원소의 title과 id 추출;
            return new BgmListResponse(bgms);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
