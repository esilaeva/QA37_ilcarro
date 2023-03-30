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
    public void registrationEmptyName(){
        User user = new User()
                .setFirstName("")
                .setLastName("Simpson")
                .setEmail("simpson@gmail.com")
                .setPassword("Ss12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(),"Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void registrationEmptyLastName(){
        User user = new User()
                .setFirstName("Egor")
                .setLastName("")
                .setEmail("e.simpson@gmail.com")
                .setPassword("Ss12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(),"Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
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

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Wrong email format\n" +
                "Wrong email format");
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

        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
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

        Assert.assertEquals(app.getHelperUser().getMessage(),"\"User already exists\"");
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeWindow();


    }


}
