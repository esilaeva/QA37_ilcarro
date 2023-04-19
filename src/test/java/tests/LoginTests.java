package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import models.User;

public class LoginTests extends TestBase {
    Logger logger = LoggerFactory.getLogger(AddNewCarTests.class);

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess() {
        User user = new User().setEmail("b.snyder@gmail.com").setPassword("Tt12345$");
        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.snyder@gmail.com", "Tt12345$");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

    }

    @Test
    public void loginSuccessModel() {
        logger.info("Test start with test data --->/n" + "email: 'b.snyder@gmail.com' & password: 'Tt12345$'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.snyder@gmail.com", "Tt12345$");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

    }

    @Test
    public void loginWrongEmail() {
        logger.info("Test negative check if it possible to login with wrong format email ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.snyder\\u041F@gmail.com", "Tt12345$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginWrongPassword() {
        logger.info("Test negative check if it possible to login with wrong format password ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("b.snyder@gmail.com", "Tt12345");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }

    @Test
    public void loginUnregistered() {
        logger.info("Test negative check if it possible to login with valid format data unregistered user ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("luck@gmail.com", "luck12345$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().closeWindow();
    }
}

