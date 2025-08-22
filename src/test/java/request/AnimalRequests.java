package request;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Step;
import utilities.ApiLogger;
import utilities.BaseRequest;
import utilities.BaseTest;

public class AnimalRequests extends BaseRequest {

    public AnimalRequests(APIRequestContext request) {
        super(request);
    }

    @Step("(GET) Obtener Animales")
    public APIResponse obtenerAnimales(RequestOptions requestOptions) {
        response = request.get("animales", requestOptions);
        ApiLogger.logApi(response, BaseTest.Method.GET);

        return response;
    }

    @Step("(GET) Obtener Animal")
    public APIResponse obtenerAnimal(int id, RequestOptions requestOptions) {
        response = request.get("animales/" + id);
        ApiLogger.logApi(response, BaseTest.Method.GET);

        return response;
    }

    @Step("(POST) crear Animal")
    public APIResponse crearAnimal(RequestOptions requestOptions) {
        response = request.post("animales", requestOptions);
        ApiLogger.logApi(response, BaseTest.Method.POST);

        return response;
    }

    @Step("(PUT) Actualizar Animal")
    public APIResponse actualizarAnimal(int id, RequestOptions requestOptions) {
        final var url = String.format("animales/%d", id);
        response = request.put(url, requestOptions);
        ApiLogger.logApi(response, BaseTest.Method.PUT);

        return response;
    }

    @Step("(PATCH) Actualizar Parcialmente Animal")
    public APIResponse actualizarParcialmenteAnimal(int id, RequestOptions requestOptions) {
        final var url = String.format("animales/%d", id);
        response = request.patch(url, requestOptions);
        ApiLogger.logApi(response, BaseTest.Method.PATCH);

        return response;
    }

    @Step("(DELETE) Eliminar Animal")
    public APIResponse eliminarAnimal(int id, RequestOptions requestOptions) {
        final var url = String.format("animales/%d", id);
        response = request.delete(url, requestOptions);
        ApiLogger.logApi(response, BaseTest.Method.DELETE);

        return response;
    }
}
