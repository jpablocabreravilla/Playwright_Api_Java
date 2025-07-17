package utilities;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

public class CustomOptions implements OptionsFactory {

    @Override
    public Options getOptions() {
        return new Options().setApiRequestOptions(createApiRequestOptions());
    }

    private APIRequest.NewContextOptions createApiRequestOptions() {
        return new APIRequest.NewContextOptions().setBaseURL("http://");
    }
}