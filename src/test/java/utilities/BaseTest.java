package utilities;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import request.AuthRequests;

@UsePlaywright(CustomOptions.class)
public class BaseTest {
    protected APIResponse response;
    protected RequestOptions requestOptions;
    protected Gson gson = new Gson();

    @BeforeEach
    public void masterSetUp() {
        requestOptions = RequestOptions.create();
    }

    @AfterEach
    public void masterTearDown() {
    }

    public enum Method {
        GET,
        POST,
        PUT,
        PATCH,
        DELETE
    }

    protected void initAuth(APIRequestContext request, RequestOptions requestOptions) {
        final var authRequests = new AuthRequests(request);
        final var requestBody = """
                {
                    "username": "standard_user",
                    "password": "secret_blass_academy"
                }
                """;

        requestOptions.setData(requestBody);
        requestOptions.setHeader("Content-Type", "application/json");
        response = authRequests.login(requestOptions);

        final var documentContext = JsonPath.parse(response.text());
        final var token = documentContext.read("accessToken");
        requestOptions.setHeader("Authorization", "Bearer " + token);
    }
}


