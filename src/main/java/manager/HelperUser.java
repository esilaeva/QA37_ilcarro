package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//*[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath("//input[@autocomplete='username']"), email);
        type(By.xpath("//input[@autocomplete='current-password']"), password);
    }

    public void submitLogin() {
        click(By.xpath("//button[@type='submit']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[@href='/logout?url=%2Fsearch'][text()=' Logout ']"));
    }

    public boolean isDeleteAccount() {
        return isElementPresent(By.xpath("//a[@class='navigation-link ng-star-inserted']"));
    }

    public boolean isFindCar() {
        return isElementPresent(By.xpath("//h1[@class='title'][text()='Find your car now!']"));
    }

    public boolean isSignUp() {
        return isElementPresent(By.xpath("//*[@href='/registration?url=%2Fsearch']"));
    }

    public void submitOk() {
        click(By.xpath("//button[@class='positive-button ng-star-inserted']"));
    }

    public void logout() {
        click(By.xpath("//a[@href='/logout?url=%2Fsearch'][text()=' Logout ']"));
    }
}
