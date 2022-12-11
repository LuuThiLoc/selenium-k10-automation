package model.components;

public abstract class LoginPage {

    protected void login() {
        System.out.println(usernameLocator());
        System.out.println(passwordLocator());
        System.out.println(loginBtnLocator());
    }

    protected void verifyLoginSuccess() {
        System.out.println("Verifying Dashboard Page display");
    }

    protected abstract String usernameLocator();

    protected abstract String passwordLocator();

    protected abstract String loginBtnLocator();

}
