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

    @Test
    public void crearAnimalTest(APIRequestContext request) {
        final var requestBody = """
                {
                    "nombre": "Nova",
                    "tipo": "cautivo",
                    "edad": 3,
                    "peso": 42.3,
                    "genero": "hermafrodita",
                    "amo": {
                        "nombre": "Dovie",
                        "apellido": "McLaughlin",
                        "correo": "Dovie_McLaughlin42@hotmail.com",
                        "edad": 52,
                        "pais": "Paraguay"
                    }
                }
                """;

        requestOptions.setData(requestBody);
        requestOptions.setHeader("Content-Type", "application/json");

        response = request.post("animales", requestOptions);
        ApiLogger.logApi(response, Method.POST);

        Assertions.assertEquals(201, response.status());
    }

    @Test
    public void actualizarAnimalTest(APIRequestContext request) {
        final var requestBody = """
                {
                    "nombre": "Curso en Vivo",
                    "tipo": "domestico",
                    "edad": 25,
                    "peso": 32.00,
                    "genero": "hembra",
                    "amo": {
                        "nombre": "Christophe",
                        "apellido": "Stanton",
                        "correo": "Christophe_Stanton52@hotmail.com",
                        "edad": 30,
                        "pais": "Guam"
                    }
                }
                """;

        requestOptions.setData(requestBody);
        requestOptions.setHeader("Content-Type", "application/json");

        response = request.put("animales/5", requestOptions);
        ApiLogger.logApi(response, Method.PUT);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void actualizarParcialmenteAnimalTest(APIRequestContext request) {
        final var requestBody = """
                {
                    "nombre": "Hola",
                    "tipo": "Mundo",
                    "edad": 25,
                    "peso": 32.00
                }
                """;

        requestOptions.setData(requestBody);
        requestOptions.setHeader("Content-Type", "application/json");

        response = request.patch("animales/5", requestOptions);
        ApiLogger.logApi(response, Method.PATCH);

        Assertions.assertEquals(200, response.status());
    }

}
