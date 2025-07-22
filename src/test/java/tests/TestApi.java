package tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestApi {

    private Playwright playwright;
    private APIRequestContext request;

    @BeforeAll
    void setup() {
        playwright = Playwright.create();

        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("https://petstore.swagger.io/v2/")
                .setExtraHTTPHeaders(headers));
    }

    @Test
    void shouldReturnSoldPets() {
        APIResponse response = request.get("pet/findByStatus?status=sold");

        System.out.println("Status: " + response.status());
        System.out.println("Status Text: " + response.statusText());
        System.out.println("OK: " + response.ok());
        System.out.println("Body: " + response.text());

        assertTrue(response.ok(), "La respuesta debe ser OK");
        assertEquals(200, response.status(), "Código de estado debe ser 200");
        assertEquals("OK", response.statusText(), "Texto de estado debe ser OK");
        assertTrue(response.text().contains("sold"), "Debe contener pets con estado sold");
    }

    @AfterAll
    void tearDown() {
        if (request != null) request.dispose();
        if (playwright != null) playwright.close();
    }
}
