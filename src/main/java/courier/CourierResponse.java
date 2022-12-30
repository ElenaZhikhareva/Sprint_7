package courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import login.Login;

import static io.restassured.RestAssured.given;

public class CourierResponse {
    @Step("Создание курьера")
    public ValidatableResponse getCourierResponse(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Логин курьера в системе")
    public ValidatableResponse getLoginResponse(Login login) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(login)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }
}