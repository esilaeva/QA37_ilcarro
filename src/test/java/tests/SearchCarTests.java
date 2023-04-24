package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase {

    @BeforeMethod
    public void homePage(){
        app.getHelperCar().goHomePage();
    }

    @Test
    public void searchCurrentMonthSuccess() {
        app.getHelperCar().searchCurrentMonth("Tel Aviv, Israel", "4/27/2023", "4/28/2023");
        app.getHelperCar().submit();
//app.getHelperCar().pause(5000);
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess() {
        app.getHelperCar().searchCurrentYear("Tel Aviv, Israel", "05/20/2023", "12/12/2023");
        app.getHelperCar().submit();
   //     app.getHelperCar().pause(5000);
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess() {
        app.getHelperCar().searchAnyPeriod("Tel Aviv, Israel", "4/29/2023", "3/30/2024");
        app.getHelperCar().submit();
   //     app.getHelperCar().pause(5000);
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

}
