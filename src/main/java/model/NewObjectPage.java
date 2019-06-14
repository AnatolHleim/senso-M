package model;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewObjectPage extends AbstractPage {
    public NewObjectPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    private static final String URL_NEW_OBJECT = AbstractPage.BASE_URL;
    public NewObjectPage openPage() {
        log.info("Opening page: " + URL_NEW_OBJECT);
        openUrl(URL_NEW_OBJECT);
        log.info("Page opened!");
        return new NewObjectPage(driver,log);
    }

    @FindBy (xpath = "//div[@class='title']")
    WebElement titleNewObject;
    @FindBy (xpath = "//div[@class='label'][1]/div[@class='input datalist']/input")
    public WebElement ownerInput;
    @FindBy (xpath = "//div[@class='label'][2]/div[@class='input datalist']/input")
    WebElement firstEngeneerInput;
    @FindBy (xpath = "//div[@class='label'][3]/div[@class='input datalist']/input")
    WebElement secondEngeneerInput;
    @FindBy (xpath = "//div[@class='label'][4]/div[@class='input datalist']/input")
    WebElement managerInput;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'Название')]]//div/input")
    WebElement nameObject;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'Адрес')]]//div/input")
    WebElement adressObject;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'URL')]]//div/input")
    WebElement URLObject;
    @FindBy (xpath = "//div[@class='label useAuthentication']/label/span")
    WebElement checkBoxAuth;
    @FindBy (xpath = "//div[@class='label logPass'][1]/div[@class='input']/input")
    WebElement loginInput;
    @FindBy (xpath = "//div[@class='label logPass'][2]/div[@class='input']/input")
    WebElement passwordInput;

}
