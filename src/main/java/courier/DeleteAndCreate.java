package courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import login.Login;

import static io.restassured.RestAssured.given;

public class DeleteAndCreate {

    public final String existingLogin = "elena";
    public final String existingLoginPassword = "12345";
    public final String existingLoginFirstName = "lalala";

    @Step("Удаление логина")
    public void deleteLogin() {
        Login login = new Login(existingLogin, existingLoginPassword);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(login)
                        .when()
                        .post("/api/v1/courier/login");
        String id = response.jsonPath().getString("id");
        given()
                .when()
                .delete("/api/v1/courier/login" + id);
    }

    @Step("Создание аккаунта")
    public void createAccount() {
        Courier successfulCourier = new Courier(existingLogin, existingLoginPassword, existingLoginFirstName);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(successfulCourier)
                .when()
                .post("/api/v1/courier");
    }
}