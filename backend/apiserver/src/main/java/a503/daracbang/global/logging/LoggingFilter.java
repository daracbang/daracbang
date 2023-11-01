package a503.daracbang.global.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoggingFilter extends OncePerRequestFilter {

    private final String LOG_TEXT  = "Filter Logs : Method = {}; " +
                                     "REQUEST URI= {}; " +
                                     "REQUEST BODY = {} " +
                                     "RESPONSE CODE = {}; " +
                                     "RESPONSE BODY = {}; " +
                                     "TIME TAKEN = {}ms";

    private final String LOG_NO_BODY_TEXT  = "Filter Logs : Method = {}; " +
                                             "REQUEST URI= {}; " +
                                             "RESPONSE CODE = {}; " +
                                             "TIME TAKEN = {}ms";

    Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        chain.doFilter(request, response);

        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

        long timeTaken = System.currentTimeMillis() - startTime;

        String method = request.getMethod();
        if (method.equals("GET") || method.equals("DELETE")) {
            LOGGER.info(LOG_NO_BODY_TEXT,
                method,
                request.getRequestURI(),
                response.getStatus(),
                timeTaken);
        }
        else {
            LOGGER.info(LOG_TEXT,
                method,
                request.getRequestURI(),
                requestBody,
                response.getStatus(),
                responseBody,
                timeTaken
            );
        }
        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
