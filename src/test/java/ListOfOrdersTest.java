import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ListOfOrdersTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @DisplayName("Проверка списка заказов")
    @Test
    public void listOfOrdersTest() {
        Response response = given()
                .when()
                .header("Content-type", "application/json")
                .get("/api/v1/orders");
        response.then().log().all();
        MatcherAssert.assertThat("orders", notNullValue());
    }
}