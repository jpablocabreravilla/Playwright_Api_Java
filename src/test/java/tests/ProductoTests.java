package tests;

import com.microsoft.playwright.APIRequestContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.ApiLogger;
import utilities.BaseTest;

public class ProductoTests extends BaseTest {

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

    @Test
    public void ordenarProductosTest(APIRequestContext request) {
        requestOptions.setQueryParam("sortBy", "precio");
        requestOptions.setQueryParam("order", "asc");

        response = request.get("productos", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void buscarProductosTest(APIRequestContext request) {
        requestOptions.setQueryParam("nombre", "haricot beans");

        response = request.get("productos", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void filtrarProductosTest(APIRequestContext request) {
        requestOptions.setQueryParam("filterBy", "perecible");
        requestOptions.setQueryParam("value", "false");

        response = request.get("productos", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void crearProductoTest(APIRequestContext request) {
        final var requestBody = """
        {
            "nombre": "Blass",
            "precio": 18.775,
            "cantidad": 9,
            "peso": 9.648,
            "perecible": true,
            "volumen": 42.883,
            "criticas": [
                {
                    "puntaje": 3.79,
                    "comentario": "Tenderly braised lamb in a rich sesame seed and brussels sprouts sauce",
                    "correo": "Geraldine.Homenick91@yahoo.com",
                    "usuario": "Geraldine_Homenick29"
                },
                {
                    "puntaje": 2.24,
                    "comentario": "Fresh and crispy salad with homemade dressing",
                    "correo": "Joanne.Larson92@hotmail.com",
                    "usuario": "Joanne_Larson92"
                }
            ],
            "etiquetas": [
                "Soft",
                "Intelligent",
                "Elegant",
                "Electronic",
                "Incredible",
                "Small",
                "Handcrafted",
                "Licensed",
                "Oriental"
            ]
        }
        """;

        requestOptions.setData(requestBody);
        requestOptions.setHeader("Content-Type", "application/json");

        response = request.post("productos", requestOptions);
        ApiLogger.logApi(response, Method.POST);

        Assertions.assertEquals(201, response.status());
    }

    @Test
    public void actualizarProductoTest(APIRequestContext request) {
        final var requestBody = """
        {
            "nombre": "Blass Vivo 2",
            "precio": 39.99,
            "cantidad": 9,
            "peso": 9.648,
            "perecible": true,
            "volumen": 42.883,
            "criticas": [
                {
                    "puntaje": 3.79,
                    "comentario": "Tenderly braised lamb in a rich sesame seed and brussels sprouts sauce",
                    "correo": "Geraldine.Homenick91@yahoo.com",
                    "usuario": "Geraldine_Homenick29"
                },
                {
                    "puntaje": 2.24,
                    "comentario": "Fresh and crispy salad with homemade dressing",
                    "correo": "Joanne.Larson92@hotmail.com",
                    "usuario": "Joanne_Larson92"
                }
            ],
            "etiquetas": [
                "Soft",
                "Intelligent",
                "Elegant",
                "Electronic",
                "Incredible",
                "Small",
                "Handcrafted",
                "Licensed",
                "Oriental"
            ]
        }
        """;

        requestOptions.setData(requestBody);
        requestOptions.setHeader("Content-Type", "application/json");

        response = request.put("productos/2", requestOptions);
        ApiLogger.logApi(response, Method.PUT);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    public void actualizarParcialmenteProductoTest(APIRequestContext request) {
        final var requestBody = """
        {
            "nombre": "Blass",
            "precio": 4.892,
            "cantidad": 3,
            "peso": 3.71,
            "perecible": false,
            "volumen": 58.396
        }
        """;

        requestOptions.setData(requestBody);
        requestOptions.setHeader("Content-Type", "application/json");

        response = request.patch("productos/2", requestOptions);
        ApiLogger.logApi(response, Method.PATCH);

        Assertions.assertEquals(200, response.status());
    }

}
