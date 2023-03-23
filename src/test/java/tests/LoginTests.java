package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
        // app.getHelperUser().submitOk();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@gmail.com", "Tt12345$");
        app.getHelperUser().submitLogin();
        //app.getHelperUser().submitOk();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginUnregisteredUser() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("snyder@gmail.com", "Tt12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageLoginFailed(), "\"Login or Password incorrect\"");
    }


    @Test
    public void loginWrongEmailBlank() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("", "Tt12345$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//*[@type='submit'][@disabled]")));
        //Assert.assertEquals(app.getHelperUser().messageWrongEmailFormat(), "Email is required");
    }

    @Test
    public void loginWrongEmailSpace() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(" ", "Tt12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageWrongEmailFormat(), "Email is required");
    }

    @Test
    public void loginWrongEmailWithoutAt() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().clear(By.xpath("//*[@id='email']"));
        app.getHelperUser().fillLoginForm("b.ortizgmail.com", "Tt12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageWrongEmailFormat(), "It'snot look like email");
    }

    @Test
    public void loginWrongEmailWithTwoAt() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@@gmail.com", "Tt12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageWrongEmailFormat(), "It'snot look like email");
    }

    @Test
    public void loginWrongEmailBlankBeforAt() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("@gmail.com", "Tt12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageWrongEmailFormat(), "It'snot look like email");
    }

    @Test
    public void loginWrongEmailBlankAfterAtBeforDot() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@.com", "Tt12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageWrongEmailFormat(), "It'snot look like email");
    }

    @Test
    public void loginWrongEmailBlankAfterAt() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@", "Tt12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageWrongEmailFormat(), "It'snot look like email");
    }

    @Test
    public void loginWrongEmailWithRusChar() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.оrtiz@gmail.com", "Tt12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageWrongEmailFormat(), "It'snot look like email");
    }

    @Test
    public void loginWrongEmailWithoutDot() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@gmailcom", "Tt12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageLoginFailed(), "\"Login or Password incorrect\"");
    }

    @Test
    public void loginWrongPasswordBlank() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@gmail.com", "");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//*[@type='submit'][@disabled]")));
    }

    @Test
    public void loginWrongPasswordWithRusChar() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("p.mоntfort@gmail.com", "Tоst12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageWrongEmailFormat(), "It'snot look like email");
    }

    @Test
    public void loginWrongPasswordWithoutUpperCase() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@gmail.com", "t12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageLoginFailed(), "\"Login or Password incorrect\"");
    }

    @Test
    public void loginWrongPasswordSpace() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@gmail.com", " ");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageLoginFailed(), "\"Login or Password incorrect\"");
    }

    @Test
    public void loginWrongPasswordWithoutSpecChar() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@gmail.com", "Tt12345");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageLoginFailed(), "\"Login or Password incorrect\"");
    }

    @Test
    public void loginWrongPasswordWithoutLowCase() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@gmail.com", "TT12345$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageLoginFailed(), "\"Login or Password incorrect\"");
    }

    @Test
    public void loginWrongPasswordWithoutNumber() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@gmail.com", "TtTtTtTt$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageLoginFailed(), "\"Login or Password incorrect\"");
    }

    @Test
    public void loginWrongPasswordLess8Symbols() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@gmail.com", "Tt1234$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageLoginFailed(), "\"Login or Password incorrect\"");
    }

    @Test
    public void loginWrongPasswordMore15Symbols() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.ortiz@gmail.com", "Tt1234567890123$");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().messageLoginFailed(), "\"Login or Password incorrect\"");
    }

    @AfterMethod
    public void postCondition() {
        if (!app.getHelperUser().isElementPresent(By.xpath("//*[@class='error']"))
                && !app.getHelperUser().isElementPresent(By.xpath("//div[@class='error ng-star-inserted']//div[text()]"))
                && !app.getHelperUser().isElementPresent(By.xpath("//button[@disabled]")))
        {
            app.getHelperUser().submitOk();
        }

    }
}