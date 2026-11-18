package com.pruebas.bdd.steps;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class InscripcionStepDefinitions {
    private static final String ALUMNO = "Jeremy Calderon Chavez";
    private static final String CODIGO = "I202500542";

    private String codigoEstudiante;
    private Response respuesta;

    @Before
    public void configurar() {
        System.out.println("Alumno: " + ALUMNO + " Código: " + CODIGO);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    private Response inscribir(String codigo, int cursoId) {
        String body = """
                {"codigoEstudiante": "%s", "cursoId": %d}
                """.formatted(codigo, cursoId);

        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/inscripciones");
    }

    @Given("el estudiante {string} ya está inscrito en el curso {int}")
    public void elEstudianteYaEstaInscrito(String codigo, int cursoId) {
        System.out.println("Precondición: " + codigo + " inscrito en el curso " + cursoId + " (DataLoader)");
    }

    @Given("un estudiante nuevo con un código distinto en cada ejecución")
    public void unEstudianteNuevo() {
        codigoEstudiante = "I" + System.currentTimeMillis();
    }

    @When("se inscribe en el curso {int}")
    public void seInscribeEnElCurso(int cursoId) {
        respuesta = inscribir(codigoEstudiante, cursoId);
    }

    @Then("el sistema responde con código {int}")
    public void elSistemaRespondeConCodigo(int codigo) {
        assertEquals(codigo, respuesta.statusCode());
    }

    @And("la inscripción devuelta corresponde al estudiante y al curso {int}")
    public void laInscripcionCorresponde(int cursoId) {
        assertEquals(codigoEstudiante, respuesta.jsonPath().getString("codigoEstudiante"));
        assertEquals(cursoId, respuesta.jsonPath().getInt("cursoId"));
    }
}
