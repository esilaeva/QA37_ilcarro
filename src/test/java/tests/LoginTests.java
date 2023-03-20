package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (!app.getHelperUser().isSignUp()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.snyder@gmail.com", "Tt12345$");
        app.getHelperUser().submitLogin();
        app.getHelperUser().submitOk();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@gmail.com", "Tt12345$");
        app.getHelperUser().submitLogin();
        app.getHelperUser().submitOk();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

}