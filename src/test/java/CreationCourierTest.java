import courier.Courier;
import courier.CourierResponse;
import courier.DeleteAndCreate;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.equalTo;

public class CreationCourierTest extends DeleteAndCreate {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        createAccount();
    }

    @Test
    @DisplayName("Создание нового курьера и проверка, что запрос возвращает ок")
    public void createNewCourierTest(){
        CourierResponse courierResponse = new CourierResponse();
        ValidatableResponse duplicateLogin  = courierResponse.getCourierResponse(
                Courier.getRandomCourier());
        duplicateLogin
                    .statusCode(201).and()
                    .body("ok", equalTo(true));
        }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров и если проверка текста ошибки")
    public void nonDoubleCreatedCourierTest() {
        CourierResponse courierResponse = new CourierResponse();
        ValidatableResponse duplicateLogin  = courierResponse.getCourierResponse(
                new Courier(existingLogin, existingLoginPassword, existingLoginFirstName));

        duplicateLogin
                .statusCode(409).and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Проверка обязательных полей, отсутствует логин")
    public void createNewCourierWithMissingDataTest(){ //чтобы создать курьера, нужно передать в ручку все обязательные поля
        CourierResponse courierClient = new CourierResponse();
        ValidatableResponse emptyLoginField  = courierClient.getCourierResponse(
                new Courier(null, existingLoginPassword, existingLoginFirstName));
        emptyLoginField
                .statusCode(400).and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void tearDown(){
        deleteLogin();
    }
}