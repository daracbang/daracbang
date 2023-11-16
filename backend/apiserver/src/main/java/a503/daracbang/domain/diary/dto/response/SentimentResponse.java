package a503.daracbang.domain.diary.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SentimentResponse {

    private Document document;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Document {
        private String sentiment;
        private Confidence confidence;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Confidence {
        private float neutral;
        private float positive;
        private float negative;
    }
}
