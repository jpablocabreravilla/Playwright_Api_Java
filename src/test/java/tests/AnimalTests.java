package tests;

import annotations.Regression;
import com.microsoft.playwright.APIRequestContext;
import modelos.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.AnimalRequests;
import utilities.BaseTest;

public class AnimalTests extends BaseTest {

    private AnimalRequests animalRequests;

    @BeforeEach
    public void setUp(APIRequestContext request) {
        animalRequests = new AnimalRequests(request);
    }

    @Test
    @Regression
    public void obtenerAnimalesTest() {
        response = animalRequests.obtenerAnimales(requestOptions);
        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
    public void obtenerAnimalTest() {

        response = animalRequests.obtenerAnimal(5, requestOptions);
        Assertions.assertEquals(200, response.status());
    }

/*  @Test
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
    }*/

    @Test
    @Regression
    public void obtenerAnimalTestAssertions() {


        response = animalRequests.obtenerAnimal(5, requestOptions);
        Assertions.assertEquals(200, response.status());

        final var animal = gson.fromJson(response.text(), Animal.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals(5, animal.id()),
                () -> Assertions.assertEquals("Fido", animal.nombre()),
                () -> Assertions.assertEquals(42.3, animal.peso()),
                () -> Assertions.assertEquals("Dora", animal.amo().nombre()),
                () -> Assertions.assertEquals(52, animal.amo().edad())
        );
    }

    @Test
    @Regression
    public void eliminarAnimalTest() {

        response = animalRequests.eliminarAnimal(5, requestOptions);
        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
    public void ordenarAnimalesTest() {
        // Endpoint resultante: /animales?sortBy=edad&order=desc
        requestOptions.setQueryParam("sortBy", "edad");
        requestOptions.setQueryParam("order", "desc");
        response = animalRequests.obtenerAnimales(requestOptions);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
    public void buscarAnimalesTest() {
        requestOptions.setQueryParam("nombre", "Lola");
        response = animalRequests.obtenerAnimales(requestOptions);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
    public void filtrarAnimalesTest() {
        requestOptions.setQueryParam("filterBy", "tipo");
        requestOptions.setQueryParam("value", "domestico");
        response = animalRequests.obtenerAnimales(requestOptions);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
    public void crearAnimalTest() {
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
        response = animalRequests.crearAnimal( requestOptions);
        Assertions.assertEquals(201, response.status());
    }

    @Test
    @Regression
    public void actualizarAnimalTest() {
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
        response = animalRequests.actualizarAnimal(5, requestOptions);

        Assertions.assertEquals(200, response.status());
    }

    @Test
    @Regression
    public void actualizarParcialmenteAnimalTest() {
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
        response = animalRequests.actualizarParcialmenteAnimal(5, requestOptions);

        Assertions.assertEquals(200, response.status());
    }

}
