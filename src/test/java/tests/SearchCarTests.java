package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase {

    @BeforeMethod
    public void homePage(){
        app.getHelperCar().goHomePage();
        app.getHelperCar().clearTextBox(By.id("dates"));
    }

    @Test
    public void searchCurrentMonthSuccess() {
        app.getHelperCar().searchCurrentMonth("Tel Aviv, Israel", "4/27/2023", "4/28/2023");
        app.getHelperCar().getScreen("src/test/screenshots/current.png");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess() {
        app.getHelperCar().searchCurrentYear("Tel Aviv, Israel", "05/20/2023", "12/12/2023");
        app.getHelperCar().getScreen("src/test/screenshots/currentYear.png");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess() {
        app.getHelperCar().searchAnyPeriod("Tel Aviv, Israel", "4/29/2023", "3/30/2024");
        app.getHelperCar().getScreen("src/test/screenshots/any.png");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void negativeSearch(){
        app.getHelperCar().searchNotValidPeriod("Tel Aviv, Israel", "1/10/2023", "10/10/2023");

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperUser().getMessageWrongDate(), "You can't pick date before today");
    }
}
