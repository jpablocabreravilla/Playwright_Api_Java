package tests;

import com.microsoft.playwright.APIRequestContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.ApiLogger;
import utilities.BaseTest;

public class ProductosTests extends BaseTest {

    @Test
    public void obtenerProductosTest(APIRequestContext request) {
        response = request.get("productos");
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void obtenerProductoTest(APIRequestContext request) {
        response = request.get("productos/2");
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void eliminarProductoTest(APIRequestContext request) {
        response = request.delete("productos/2");
        ApiLogger.logApi(response, Method.DELETE);

        Assertions.assertEquals(200, response.status());
    }
    
}
