package utilities;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import java.util.HashMap;
import java.util.Map;

public class BaseAPITest {
    protected static Playwright playwright;
    protected static APIRequestContext request;

    public static void setupAPIRequestContext(String baseURL) {
        playwright = Playwright.create();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(baseURL)
                .setExtraHTTPHeaders(headers));
    }

    public static void tearDownAPI() {
        if (request != null) {
            request.dispose();
            request = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
