package utilities;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@UsePlaywright(CustomOptions.class)
public class BaseTest {
    protected APIResponse response;

    @BeforeEach
    public void masterSetUp() {
        Logs.info("Soy la precondicion del padre");
    }

    @AfterEach
    public void masterTearDown() {
        Logs.info("Soy la postcondicion del padre");
    }

    public enum Method {
        GET,
        POST,
        PUT,
        PATCH,
        DELETE
    }
}


