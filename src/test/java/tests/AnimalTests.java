package tests;

import annotations.Regression;
import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIRequestContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.ApiLogger;
import utilities.BaseTest;

public class AnimalTests extends BaseTest {

    @Test
    @Regression
    public void obtenerAnimalesTest(APIRequestContext request) {
        response = request.get("animales");
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
    public void obtenerAnimalTest(APIRequestContext request) {
        response = request.get("animales/5");
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
    public void obtenerAnimalTestAssertions(APIRequestContext request) {
        response = request.get("animales/5");
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());

        final var documentContext = JsonPath.parse(response.text());

        final var id = (Integer) documentContext.read("id");
        final var nombre = (String) documentContext.read("nombre");
        final var peso = (Double) documentContext.read("peso");
        final var amoNombre = (String) documentContext.read("amo.nombre");
        final var amoEdad = (Integer) documentContext.read("amo.edad");

        Assertions.assertAll(
                () -> Assertions.assertEquals(5, id),
                () -> Assertions.assertEquals("Fido", nombre),
                () -> Assertions.assertEquals(42.3, peso),
                () -> Assertions.assertEquals("Dora", amoNombre),
                () -> Assertions.assertEquals(52, amoEdad)
        );
    }

    @Test
    @Regression
    public void eliminarAnimalTest(APIRequestContext request) {
        response = request.delete("animales/5");
        ApiLogger.logApi(response, Method.DELETE);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
    public void ordenarAnimalesTest(APIRequestContext request) {
        // Endpoint resultante: /animales?sortBy=edad&order=desc
        requestOptions.setQueryParam("sortBy", "edad");
        requestOptions.setQueryParam("order", "desc");

        response = request.get("animales", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
    public void buscarAnimalesTest(APIRequestContext request) {
        requestOptions.setQueryParam("nombre", "Lola");

        response = request.get("animales", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
    public void filtrarAnimalesTest(APIRequestContext request) {
        requestOptions.setQueryParam("filterBy", "tipo");
        requestOptions.setQueryParam("value", "domestico");

        response = request.get("animales", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
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
    @Regression
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
    @Regression
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
