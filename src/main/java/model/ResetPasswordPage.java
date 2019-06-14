package model;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResetPasswordPage extends AbstractPage {
    ResetPasswordPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    private static final String URL_AUTH = AbstractPage.BASE_URL+"auth/password-reset";
    @FindBy(id = "email")
    WebElement inputUserEmail;
    @FindBy(xpath = "//p[@class='p error']")
    public WebElement errorNotAvailibleEmail;
    @FindBy (xpath = "//button[@type='submit']")
    WebElement buttonSubmit;
    public WebElement findBy(By by){
        return find(driver.findElement(by));
    }
    public void sendEmailToResetPassword(String text){
        type(text, inputUserEmail);
        click(buttonSubmit);
    }
}
