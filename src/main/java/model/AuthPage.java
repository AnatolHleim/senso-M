package model;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class AuthPage extends AbstractPage {
  private static final String URL_AUTH = "http://localhost:4200/auth/login";
  public AuthPage(WebDriver driver, Logger log) {
    super(driver, log);
  }
  public AuthPage openPage() {
    log.info("Opening page: " + URL_AUTH);
    openUrl(URL_AUTH);
    log.info("Page opened!");
    return new AuthPage(driver,log);
  }
  public MainPage logIn(String userName,String password){
    type(userName,inputUserEmail);
    type(password,inputPassword);
    click(buttonSubmit);
    return new MainPage(driver,log);
  }
  public ResetPasswordPage goToResetPasswordPage(){
    click(linkForgotPassword);
    return new ResetPasswordPage(driver,log);
  }
  public WebElement findBy(By by){
   return find(driver.findElement(by));
  }

  @FindBy(id = "email")
  WebElement inputUserEmail;
  @FindBy (id = "password")
  WebElement inputPassword;
  @FindBy (xpath = "//button[@type='submit']")
  WebElement buttonSubmit;
  @FindBy (xpath = "//button[@class='button button_disabled']")
  public WebElement buttonSubmitDisabled;
  @FindBy (css = "div.auth__forgot-password > a")
  WebElement linkForgotPassword;

}