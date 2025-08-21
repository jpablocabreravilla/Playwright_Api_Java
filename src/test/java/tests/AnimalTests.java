package tests;

import com.microsoft.playwright.APIRequestContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.ApiLogger;
import utilities.BaseTest;

public class AnimalTests extends BaseTest {

    @Test
    public void obtenerAnimalesTest(APIRequestContext request) {
        response = request.get("animales");
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void obtenerAnimalTest(APIRequestContext request) {
        response = request.get("animales/5");
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void eliminarAnimalTest(APIRequestContext request) {
        response = request.delete("animales/5");
        ApiLogger.logApi(response, Method.DELETE);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void ordenarAnimalesTest(APIRequestContext request) {
        // Endpoint resultante: /animales?sortBy=edad&order=desc
        requestOptions.setQueryParam("sortBy", "edad");
        requestOptions.setQueryParam("order", "desc");

        response = request.get("animales", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void buscarAnimalesTest(APIRequestContext request) {
        requestOptions.setQueryParam("nombre", "Lola");

        response = request.get("animales", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void filtrarAnimalesTest(APIRequestContext request) {
        requestOptions.setQueryParam("filterBy", "tipo");
        requestOptions.setQueryParam("value", "domestico");

        response = request.get("animales", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

}
