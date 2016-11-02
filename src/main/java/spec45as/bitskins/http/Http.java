package spec45as.bitskins.http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

public class Http {
    static org.slf4j.Logger log = LoggerFactory.getLogger(Http.class);
    final static long MIN_REQUEST_TIMEOUT = 200L;
    private Instant lastRequest;
    protected ObjectMapper defaultObjectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public Http() {
        lastRequest = Instant.now();
    }

    public synchronized JsonNode getJsonResponse(String url) throws IOException {
        try {
            JsonNode jsonResponse = defaultObjectMapper.readTree(getResponse(url));
            log.info("BitSkins Api JSON Response: " + jsonResponse.toString().substring(0, Math.min(256, jsonResponse.toString().length())));
            return jsonResponse;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public synchronized InputStream getResponse(String url) throws IOException {
        int attempts = 3;
        for (int attempt = 1; attempt <= attempts; attempt++) {
            try {
                log.info("BitSkins Api Request: " + url);
                long timeLast = Duration.between(lastRequest, Instant.now()).toMillis();
                if (timeLast < MIN_REQUEST_TIMEOUT) {
                    Thread.sleep(MIN_REQUEST_TIMEOUT - timeLast);
                }
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                InputStream response;
                try {
                    response = connection.getInputStream();
                } catch (FileNotFoundException exception) {
                    response = connection.getErrorStream();
                }

                /*
                int status = ((HttpURLConnection) connection).getResponseCode();

                if (status != 200) {
                    throw new IOException("Connection error");
                }

                log.info("BitSkins Api Response Code: " + status);
                */

                return response;
            } catch (Exception e) {
                if (attempt == attempts) {
                    throw new IOException(e);
                }
            } finally {
                lastRequest = Instant.now();
            }
        }
        throw new IOException("Unable to execute GET request!");

    }
}
