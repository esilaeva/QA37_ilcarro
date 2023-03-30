package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import models.User;
import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
//        Random random = new Random();
//        int i = random.nextInt(1000);

        int z = (int) System.currentTimeMillis()/1000;

        User user= new User().setFirstName("Lizon")
                .setLastName("Snow")
                .setEmail("l.snow+"+z+"@gmail.com")
                .setPassword("Ss12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
    }

    @Test
    public void registrationWrongEmail(){
        app.getHelperUser().pause(1000);
        User user= new User().setFirstName("Lizon")
                .setLastName("Snow")
                .setEmail("l.snowgmail.com")
                .setPassword("Ss12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//*[text()='Wrong email format']")));
    }

    @Test
    public void registrationWrongPassword(){
        int z = (int) System.currentTimeMillis()/1000;
        User user= new User().setFirstName("Lizon")
                .setLastName("Snow")
                .setEmail("l.snow+"+z+"@gmail.com")
                .setPassword("Ss1$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//*[text()='Password must contain minimum 8 symbols']")));
    }

    @Test
    public void registrationWrongExistUser(){
        User user= new User().setFirstName("Br")
                .setLastName("Snyder")
                .setEmail("b.snyder@gmail.com")
                .setPassword("Tt12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//h1[text()='Registration failed']")));
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeWindow();
        if(app.getHelperUser().isElementPresent(By.xpath("//*[text()='Password must contain minimum 8 symbols']"))
                || app.getHelperUser().isElementPresent(By.xpath("//*[text()='Wrong email format']"))){
            app.getHelperUser().checkPolicyXY();
        }
    }


}
