package model;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResetPasswordPage extends AbstractPage {
    ResetPasswordPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    @FindBy(id = "email")
    WebElement inputUserEmail;
    @FindBy(xpath = "//p[@class='p error']")
    public WebElement errorNotAvailibleEmail;
    @FindBy (xpath = "//button[@type='submit']")
    WebElement buttonSubmit;
    public void sendEmailToResetPassword(String text){
        type(text, inputUserEmail);
        click(buttonSubmit);
    }
}
