package tests;

import annotations.Regression;
import com.microsoft.playwright.APIRequestContext;
import modelos.Producto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.ApiLogger;
import utilities.BaseTest;

public class ProductoTests extends BaseTest {

    @Test
    @Regression
    public void obtenerProductosTest(APIRequestContext request) {
        response = request.get("productos");
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression    public void obtenerProductoTest(APIRequestContext request) {
        response = request.get("productos/2");
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

/*  @Test
    @Regression    public void obtenerProductoTestAssertions(APIRequestContext request) {
        response = request.get("productos/2");
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());

        final var documentContext = JsonPath.parse(response.text());

        final var id = (Integer) documentContext.read("id");
        final var nombre = (String) documentContext.read("nombre");
        final var precio = (Double) documentContext.read("precio");
        final var critica0Usuario = (String) documentContext.read("criticas[0].usuario");
        final var critica2Puntaje = (Double) documentContext.read("criticas[2].puntaje");
        final var cuartaEtiqueta = (String) documentContext.read("etiquetas[3]");

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, id),
                () -> Assertions.assertEquals("Papas fritas", nombre),
                () -> Assertions.assertEquals(15.699, precio),
                () -> Assertions.assertEquals("Fredy_Walsh95", critica0Usuario),
                () -> Assertions.assertEquals(2.28, critica2Puntaje),
                () -> Assertions.assertEquals("Antojos", cuartaEtiqueta)
        );
    }*/

    @Test
    @Regression    public void obtenerProductoTestAssertions(APIRequestContext request) {
        response = request.get("productos/2");
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());

        final var producto = gson.fromJson(response.text(), Producto.class);
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, producto.id()),
                () -> Assertions.assertEquals("fresh chillies", producto.nombre()),
                () -> Assertions.assertEquals(15.99, producto.precio()),
                () -> Assertions.assertEquals("Fredy_Walsh95", producto.criticas().get(0).usuario()),
                () -> Assertions.assertEquals(2.28, producto.criticas().get(2).puntaje()),
                () -> Assertions.assertEquals("Fresh", producto.etiquetas().get(3))
        );
    }

    @Test
    @Regression    public void eliminarProductoTest(APIRequestContext request) {
        response = request.delete("productos/2");
        ApiLogger.logApi(response, Method.DELETE);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression    public void ordenarProductosTest(APIRequestContext request) {
        requestOptions.setQueryParam("sortBy", "precio");
        requestOptions.setQueryParam("order", "asc");

        response = request.get("productos", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression    public void buscarProductosTest(APIRequestContext request) {
        requestOptions.setQueryParam("nombre", "haricot beans");

        response = request.get("productos", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression    public void filtrarProductosTest(APIRequestContext request) {
        requestOptions.setQueryParam("filterBy", "perecible");
        requestOptions.setQueryParam("value", "false");

        response = request.get("productos", requestOptions);
        ApiLogger.logApi(response, Method.GET);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression    public void crearProductoTest(APIRequestContext request) {
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
    @Regression    public void actualizarProductoTest(APIRequestContext request) {
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
    @Regression    public void actualizarParcialmenteProductoTest(APIRequestContext request) {
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
