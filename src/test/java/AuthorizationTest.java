import courier.CourierResponse;
import courier.DeleteAndCreate;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import login.Login;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class AuthorizationTest extends DeleteAndCreate {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Проверка успешной авторизации")
    public void loginCourierTest() {
        CourierResponse courierResponse = new CourierResponse();
        ValidatableResponse dataCourier = courierResponse.getLoginResponse(
                new Login(existingLogin, existingLoginPassword));
        dataCourier
                .statusCode(200).and()
                .assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Проверка неуспешной авторизации, несуществующие данные")
    public void requestNonExistentDataTest() {
        CourierResponse courierClient = new CourierResponse();
        ValidatableResponse nonExistentData = courierClient.getLoginResponse(
                Login.getRandomLoginData());

        nonExistentData
                .statusCode(404).and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Нет поля логин")
    public void NoRequiredFieldLoginCourierTest() {
        CourierResponse courierClient = new CourierResponse();
        ValidatableResponse dataCourierWithoutLogin = courierClient.getLoginResponse(
                new Login(null, existingLoginPassword));

        dataCourierWithoutLogin
                .statusCode(400).and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Нет поля пароль")
    public void NoRequiredFieldPasswordCourierTest() {
        CourierResponse courierClient = new CourierResponse();
        ValidatableResponse dataCourierWithoutPassword = courierClient.getLoginResponse(
                new Login(existingLogin, ""));

        dataCourierWithoutPassword
                .statusCode(400).and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @After
    public void tearDown() {
        deleteLogin();
    }
}
