package request;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Step;
import utilities.BaseRequest;
import utilities.BaseTest;
import utilities.ApiLogger;

public class AuthRequests extends BaseRequest {

    public AuthRequests(APIRequestContext request) {
        super(request);
    }

    @Step("(POST) Login Request")
    public APIResponse login(RequestOptions requestOptions) {
        response = request.post("auth/login", requestOptions);
        ApiLogger.logApi(response, BaseTest.Method.POST);

        return response;
    }

}
