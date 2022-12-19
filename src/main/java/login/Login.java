package login;

import org.apache.commons.lang3.RandomStringUtils;

public class Login {
    private String login;
    private String password;

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static Login getRandomLoginData() {
        return new Login(
                RandomStringUtils.randomAlphabetic(10) + "",
                RandomStringUtils.randomAlphanumeric(10) + ""
        );
    }
}