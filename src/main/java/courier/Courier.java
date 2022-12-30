package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    private final String login;
    private final String password;
    private final String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier getRandomCourier() {
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10) + "", "pass", "12"
        );
    }
}