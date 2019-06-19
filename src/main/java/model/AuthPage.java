package model;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class AuthPage extends AbstractPage {
  public static final String URL_AUTH = AbstractPage.BASE_URL+"auth/login";
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
  public boolean elementHasAttribute(WebElement element,String attribute ,String active) {
    return element.getAttribute(attribute).contains(active);
  }
  public WebElement findBy(By by){
   return find(driver.findElement(by));
  }

  @FindBy(id = "email")
  public WebElement inputUserEmail;
  @FindBy (id = "password")
  public WebElement inputPassword;
  @FindBy (xpath = "//button[@type='submit']")
  public WebElement buttonSubmit;
  @FindBy (xpath = "//button[@class='button button_disabled']")
  public WebElement buttonSubmitDisabled;
  @FindBy (css = "div.auth__forgot-password > a")
  WebElement linkForgotPassword;
  @FindBy (xpath = "//div[@class='auth__password-eye']")
  public WebElement buttonShowPassword;
  @FindBy (xpath = "//span[@class='link footer__left-item p1']")
  public WebElement linkFooterRequisites;
  @FindBy (xpath = "//div[@ng-reflect-klass='requisites-bg']")
  public WebElement frameModalRequisites;
  @FindBy (xpath = "//div[@class='requisites__close']")
  public WebElement buttonCloseModalRequisites;

}