package spec45as.bitskins.http;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class TestingHttp extends Http {
    static org.slf4j.Logger log = LoggerFactory.getLogger(TestingHttp.class);
    private InputStream testingInputStream;

    public TestingHttp(InputStream inputStream) {
        super();
        testingInputStream = inputStream;
    }

    public TestingHttp() {
        super();
    }

    public InputStream getTestingInputStream() {
        return testingInputStream;
    }

    public void setTestingInputStream(InputStream testingInputStream) {
        this.testingInputStream = testingInputStream;
    }

    @Override
    public synchronized JsonNode getJsonResponse(String url) throws IOException {
        try {
            JsonNode jsonResponse = defaultObjectMapper.readTree(testingInputStream);
            log.debug("BitSkins Api JSON Response: " + jsonResponse.toString().substring(0, Math.min(256, jsonResponse.toString().length())));
            return jsonResponse;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
