package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
//        type(By.id("price"), String.valueOf(car.getPrice()));
        type(By.id("price"), car.getPrice() + "");
        type(By.id("about"), car.getAbout());
    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);

//        select.selectByIndex(5);
//        select.selectByValue("Gas");
//        select.selectByVisibleText(" Gas ");
    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnToHome() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.cssSelector("#photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        //clearDate(wd.findElement(By.id("dates")));
        //clearTextBox(By.id("dates"));
        click(By.id("dates"));

        String[] arFrom = dateFrom.split("/");
        String[] arTo = dateTo.split("/");

        String locatorFrom = "//div[text()=' " + arFrom[1] + " ']";
        click(By.xpath(locatorFrom));
        String locatorTo = "//div[text()=' " + arTo[1] + " ']";
        click(By.xpath(locatorTo));
    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.xpath("//div[@class = 'cars-container ng-star-inserted']"));
    }

    public void goHomePage() {
        wd.navigate().to("https://ilcarro.web.app/search");
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        //clearDate(wd.findElement(By.id("dates")));
   //     clearTextBox(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        System.out.println("Today ---> " + now);
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
//        LocalDate from1 = LocalDate.parse("2013:23/05", DateTimeFormatter.ofPattern("yyyy:dd/MM"));
//        System.out.println(from);
//        System.out.println(from1);

        int diffMonth = from.getMonthValue() - month;
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }

        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        diffMonth = to.getMonthValue() - from.getMonthValue();
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }

        String locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));

    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        //clearDate(wd.findElement(By.id("dates")));
      //  clearTextBox(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int diffMonth;

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffYear = from.getYear() - year;
        if (diffYear > 0) {
            diffMonth = 12 - month + from.getMonthValue();
        } else {
            diffMonth = from.getMonthValue() - month;
        }
        clickNextMonthBtn(diffMonth);
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        diffYear = to.getYear() - from.getYear();
        System.out.println("diffYear from: " + diffYear);
        if (diffYear > 0) {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        } else {
            diffMonth = to.getMonthValue() - from.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        click(By.xpath("//div[text()=' " + to.getDayOfMonth() + " ']"));
    }

    public void searchNotValidPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        WebElement element = wd.findElement(By.id("dates"));
        //click(By.id("dates"));
       // clearTextBox(By.id("dates"));
        element.sendKeys(dateFrom + " - " + dateTo);
        click(By.xpath("//div[@class='search-card']"));




       // click(By.xpath("//div[@class='error ng-star-inserted']"));
       // element.sendKeys(Keys.BACK_SPACE);
//  1/26/2023 - 4/30/2023
    }


}
