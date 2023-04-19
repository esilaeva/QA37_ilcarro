package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        //click(By.cssSelector("a[href='/login?url=%2Fsearch']"));
        //click(By.cssSelector("a[href ^='/login']"));
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public String getMessageWrongEmail() {
        return wd.findElement(By.xpath("//*[text()='Wrong email format']")).getText();
    }

    public String getMessageWrongPassword() {
        return wd.findElement(By.xpath("//*[text()='Password must contain minimum 8 symbols']")).getText();
    }

    public Boolean isMessageFail() {
        return super.isElementPresent(By.xpath("//*[text()='Registration failed']"));
    }

    public boolean isMessageWrongEmail() {
        return super.isElementPresent(By.xpath("//*[text()='Wrong email format']"));
    }

    public boolean isMessageWrongPassword() {
        return super.isElementPresent(By.xpath("//*[text()='Wrong email format']"));
    }


    public void closeWindow() {
        if (isElementPresent(By.xpath("//button[text()='Ok']")))
            click(By.xpath("//button[text()='Ok']"));

    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
    }

    public void logout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public String getErrorText() {
        String text = wd.findElement(By.cssSelector("div.error")).getText();
        System.out.println(text);

        return text;
    }

    public boolean isYallaButtonNotActive() {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));
        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result = element.isEnabled();
        return res && !result;
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        //click(By.id("terms-of-use"));
        click(By.cssSelector("label[for='terms-of-use']"));

        //
//        JavascriptExecutor js = (JavascriptExecutor) wd;
//        js.executeScript("document.querySelector('#terms-of-use').click();");
    }

    public void checkPolicyXY() {
        if (!wd.findElement(By.id("terms-of-use")).isSelected()) {
            WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));

            Rectangle rect = label.getRect();
            int w = rect.getWidth();
            int xOffSet = -w / 2;
            Actions actions = new Actions(wd);
            actions.moveToElement(label, xOffSet, 0).click().release().perform();
        }
    }


    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        closeWindow();
    }
}