package tests;

import models.Car;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase {
    Logger logger = LoggerFactory.getLogger(AddNewCarTests.class);

    @BeforeClass

    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("b.snyder@gmail.com").setPassword("Tt12345$"));
            logger.info("Logout complete");
        }
    }

    @Test
    public void addNewCarSuccessAll() {
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-" + i)
                .price(50.0)
                .about("Very nice car")
                .build();
        logger.info("Test start with test data ---> " + car.toString());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("C:\\Users\\97253\\Documents\\QA37Auto\\QA37_ilcarro\\car.jpg");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(), car.getManufacture() + " " + car.getModel() + " added successful");
    }

    @Test
    public void addNewCarSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("BMW")
                .model("M5")
                .year("2022")
                .fuel("Petrol")
                .seats(5)
                .carClass("C")
                .carRegNumber("568-940-" + i)
                .price(10.0)
                .build();
        logger.info("Test start with test data ---> " + car.toString());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(), car.getManufacture() + " " + car.getModel() + " added successful");
    }

    @AfterMethod
    public void posCondition() {
        app.getHelperCar().returnToHome();
    }

}
