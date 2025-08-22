package tests.auth;

import annotations.Regression;
import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIRequestContext;
import modelos.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.AuthRequests;
import utilities.BaseTest;

public class LoginTests extends BaseTest {
    private AuthRequests authRequests;

    @BeforeEach
    public void setUp(APIRequestContext request) {
        authRequests = new AuthRequests(request);
    }

    @Test
    @Regression
    public void loginTest() {
        final var requestBody = """
                {
                    "username": "standard_user",
                    "password": "secret_blass_academy"
                }
                """;

        requestOptions.setData(requestBody);
        requestOptions.setHeader("Content-Type", "application/json");

        response = authRequests.login(requestOptions);

        Assertions.assertEquals(200, response.status());

        final var documentContext = JsonPath.parse(response.text());
        final var token = (String) documentContext.read("accessToken");
        System.out.println(token);

        // valida la longitud del token
        Assertions.assertTrue(token.length() > 200);
    }

    @Test
    @Regression
    public void wrongUser() {
        final var requestBody = """
                {
                    "username": "wrong_user",
                    "password": "secret_blass_academy"
                }
                """;

        requestOptions.setData(requestBody);
        requestOptions.setHeader("Content-Type", "application/json");
        response = authRequests.login(requestOptions);

        final var Message = gson.fromJson(response.text(), Message.class);
        final var expected = "Usuario/Clave incorrectas";
        Assertions.assertEquals(401, response.status());
        Assertions.assertEquals(expected, Message.mensaje());

    }

}
