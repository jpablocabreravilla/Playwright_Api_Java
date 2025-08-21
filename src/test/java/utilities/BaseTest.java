package utilities;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@UsePlaywright(CustomOptions.class)
public class BaseTest {
    protected APIResponse response;
    protected RequestOptions requestOptions;

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
}


