package courier;

import io.restassured.response.ValidatableResponse;
import login.Login;

import static io.restassured.RestAssured.given;

public class CourierResponse {
    public ValidatableResponse getCourierResponse(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then();
    }

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