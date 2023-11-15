package a503.daracbang.domain.bgm.service;

import a503.daracbang.domain.bgm.dto.response.YoutubeListResponse;
import a503.daracbang.domain.bgm.dto.response.YoutubeResponse;
import a503.daracbang.domain.bgm.exception.YoutubeErrorException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static a503.daracbang.domain.bgm.exception.BgmErrorCode.YOUTUBE_ERROR_BGM;

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

    public YoutubeListResponse findMusic(String q) {
        String url = String.format("https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=%d&order=viewCount&q=%s&key=%s", maxResults, q, KEY);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            JsonNode rootNode = mapper.readTree(responseBody);
            JsonNode itemsNode = rootNode.path(ITEMS);

            List<YoutubeResponse> bgms = new ArrayList<>();
            for (int i = 0; i < maxResults; i++) {
                JsonNode itemNode = itemsNode.get(i);
                String id = itemNode.path(ITEM_ID).path(VIDEO_ID).asText();
                String title = itemNode.path(SNIPPET).path(TITLE).asText();
                bgms.add(new YoutubeResponse(id, title));
            }
            return new YoutubeListResponse(bgms);
        } catch (IOException e) {
            throw new YoutubeErrorException(YOUTUBE_ERROR_BGM);
        } catch (InterruptedException e) {
            throw new YoutubeErrorException(YOUTUBE_ERROR_BGM);
        }
    }
}
