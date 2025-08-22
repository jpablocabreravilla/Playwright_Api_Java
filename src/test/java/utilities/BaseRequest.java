package utilities;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;

public class BaseRequest {
    protected final APIRequestContext request;
    protected APIResponse response;

    public BaseRequest(APIRequestContext request) {
        this.request = request;
    }
}
