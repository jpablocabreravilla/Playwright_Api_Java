package utilities;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

import java.util.Map;

public class CustomOptions implements OptionsFactory {

    @Override
    public Options getOptions() {
        return new Options().setApiRequestOptions(createApiRequestOptions());
    }

    private APIRequest.NewContextOptions createApiRequestOptions() {
        //final var credenciales = "standard_user:secret_blass_academy";
        //final var codificado = Base64.getEncoder().encodeToString(credenciales.getBytes());
        //final var value = String.format("Basic %s", codificado);

        return new APIRequest.NewContextOptions()
                .setBaseURL("http://127.0.0.1:3000/auth/")
                .setExtraHTTPHeaders(Map.of("content-Type", "application/json"));
    }
}