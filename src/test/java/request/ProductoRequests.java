package request;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Step;
import utilities.ApiLogger;
import utilities.BaseRequest;
import utilities.BaseTest;

public class ProductoRequests extends BaseRequest {

    public ProductoRequests(APIRequestContext request) {
        super(request);
    }

    @Step("(GET) Obtener productos")
    public APIResponse obtenerProductos(RequestOptions requestOptions) {
        response = request.get("productos", requestOptions);
        ApiLogger.logApi(response, BaseTest.Method.GET);

        return response;
    }

    @Step("(GET) Obtener producto")
    public APIResponse obtenerProducto(int id, RequestOptions requestOptions) {
        response = request.get("productos/" + id);
        ApiLogger.logApi(response, BaseTest.Method.GET);

        return response;
    }

    @Step("(POST) crear producto")
    public APIResponse crearProducto(RequestOptions requestOptions) {
        response = request.post("productos", requestOptions);
        ApiLogger.logApi(response, BaseTest.Method.POST);

        return response;
    }

    @Step("(PUT) Actualizar producto")
    public APIResponse actualizarProducto(int id, RequestOptions requestOptions) {
        final var url = String.format("productos/%d", id);
        response = request.put(url, requestOptions);
        ApiLogger.logApi(response, BaseTest.Method.PUT);

        return response;
    }

    @Step("(PATCH) Actualizar Parcialmente producto")
    public APIResponse actualizarParcialmentePoducto(int id, RequestOptions requestOptions) {
        final var url = String.format("productos/%d", id);
        response = request.patch(url, requestOptions);
        ApiLogger.logApi(response, BaseTest.Method.PATCH);

        return response;
    }

    @Step("(DELETE) Eliminar producto")
    public APIResponse eliminarProducto(int id, RequestOptions requestOptions) {
        final var url = String.format("productos/%d", id);
        response = request.delete(url, requestOptions);
        ApiLogger.logApi(response, BaseTest.Method.DELETE);

        return response;
    }
}
