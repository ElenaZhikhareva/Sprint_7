import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import order.Order;
import order.OrderResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderTest {
    private final String firstNameValue;
    private final String lastNameValue;
    private final String addressValue;
    private final int metroStationValue;
    private final String phoneValue;
    private final int rentTimeValue;
    private final String deliveryDateValue;
    private final String commentValue;
    private final List<String> colorValue;

    public OrderTest(String firstNameValue, String lastNameValue, String addressValue, int metroStationValue, String phoneValue, int rentTimeValue, String deliveryDateValue, String commentValue, List<String> colorValue) {
        this.firstNameValue = firstNameValue;
        this.lastNameValue = lastNameValue;
        this.addressValue = addressValue;
        this.metroStationValue = metroStationValue;
        this.phoneValue = phoneValue;
        this.rentTimeValue = rentTimeValue;
        this.deliveryDateValue = deliveryDateValue;
        this.commentValue = commentValue;
        this.colorValue = colorValue;
    }

    @Parameterized.Parameters
    public static Object[][] getTestDataCreateOrder() {
        return new Object[][]{
                {"Иванов", "Иван", "Ленина, 15", 4, "+7 290 345 67 89", 5, "2020-06-06", "код от домофона: 1234", null},
                {"Иванов", "Иван", "Ленина, 15", 4, "+7 290 345 67 89", 5, "2020-06-06", "код от домофона: 1234", List.of("BLACK")},
                {"Иванов", "Иван", "Ленина, 15", 4, "+7 290 345 67 89", 5, "2020-06-06", "код от домофона: 1234", List.of("GREY")},
                {"Иванов", "Иван", "Ленина, 15", 4, "+7 290 345 67 89", 5, "2020-06-06", "код от домофона: 1234", List.of("BLACK", "GREY")},
        };
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @DisplayName("Создание заказа")
    @Test
    public void createOrderTest(){
        OrderResponse ordersClient = new OrderResponse();
        ValidatableResponse emptyPasswordField  = ordersClient.getOrdersResponse(
                new Order(firstNameValue, lastNameValue, addressValue,
                        metroStationValue, phoneValue, rentTimeValue, deliveryDateValue, commentValue, colorValue));
        emptyPasswordField
                .statusCode(201);
        MatcherAssert.assertThat("track", notNullValue());
    }
}