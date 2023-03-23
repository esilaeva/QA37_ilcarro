package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
    }

    public boolean isSignUp() {
        return isElementPresent(By.xpath("//*[text()=' Sign up ']"));
    }

    public void submitOk() {
        click(By.xpath("//*[text()='Ok']"));
    }

    public void logout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public String getMessage(){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.className(".dialog-container"))));

        //pause(8000);
        return wd.findElement(By.className(".dialog-container")).getText();
    }

    public String messageWrongEmailFormat(){
        if(isElementPresent(By.xpath("//div[@class='error']//div[text()]"))){
            return wd.findElement(By.xpath("//div[@class='error']//div[text()]")).getText();
        }
        return wd.findElement(By.xpath("//div[@class='error ng-star-inserted']//div[text()]")).getText();
    }

    public String messageLoginFailed(){
        String message = wd.findElement(By.xpath("//h2[@class='message']")).getText();

        return message;
    }




}
