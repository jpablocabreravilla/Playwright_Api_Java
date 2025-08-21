package tests;

import annotations.Regression;
import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIRequestContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.ApiLogger;
import utilities.BaseTest;

public class LoginTests extends BaseTest {

    @Test
    @Regression
public void loginTest(APIRequestContext request) {
        final var requestBody = """
        {
            "username": "standard_user",
            "password": "secret_blass_academy"
        }
        """;

        requestOptions.setData(requestBody);
        requestOptions.setHeader("Content-Type", "application/json");

        response = request.post("auth/login", requestOptions);
        ApiLogger.logApi(response, Method.POST);

        Assertions.assertEquals(200, response.status());

        final var documentContext = JsonPath.parse(response.text());
        final var token = (String) documentContext.read("accessToken");
        System.out.println(token);

        // valida la longitud del token
        Assertions.assertEquals(280, token.length());

    }

}
