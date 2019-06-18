package model;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    public void selectFile(String fileName) {
        log.info("Selecting '" + fileName + "' file from Files folder");
        String filePath = System.getProperty("user.dir") + "//src//main//resources//files//" + fileName;
        type(filePath, buttonLoadLogo);
        log.info("File selected");
    }

    @FindBy (xpath = "//div[@class='title']")
    WebElement titleNewObject;
    @FindBy (xpath = "//div[@class='input datalist' and preceding-sibling::div/h2[text()='Владелец процесса']]")
    public WebElement ownerField;
    @FindBy (xpath = "//div[@class='input datalist' and preceding-sibling::div/h2[text()='Инженер 1']]")
    public WebElement firstEngeneerField;
    @FindBy (xpath = "//div[@class='input datalist' and preceding-sibling::div/h2[text()='Менеджер 1']]")
    public WebElement managerField;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'Название')]]//div/input")
    public WebElement nameObjectInput;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'Название')]]//div/p[@class='error']")
    public WebElement errorNameObjectInput;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'Адрес')]]//div/input")
    public WebElement adressObjectInput;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'Адрес')]]//div/p[@class='error']")
    public WebElement errorAdressObjectInput;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'URL')]]//div/input")
    public WebElement URLObjectInput;
    @FindBy (xpath = "//div[@class='label useAuthentication']/label/span")
    public WebElement checkBoxAuth;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'Логин')]]//div/input")
    public WebElement loginInput;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'Логин')]]//div/p[@class='error']")
    public WebElement errorLoginInput;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'Пароль')]]//div/input")
    public WebElement passwordInput;
    @FindBy (xpath = "//div[div[@class='input-title']/h2[contains(text(),'Пароль')]]//div/input")
    public WebElement errorPasswordInput;
    @FindBy (xpath = "//label[@class='button ng-untouched ng-pristine ng-valid']")
    public WebElement buttonLoadLogo;
    @FindBy (xpath = "//label[@class='button ng-untouched ng-pristine ng-valid']/input")
    public WebElement inputPathLogo;
    @FindBy (xpath = "//button[text()='Выбрать из списка']")
    public WebElement buttonLoadStandartLogo;
    @FindBy (xpath = "//p[contains(text(),'Файл должен быть в формате JPG')]")
    WebElement errorForLogoLoad;
    @FindBy (xpath = "//div[@class='label snowCover']/input")
    public WebElement snowCoverInput;
    @FindBy (xpath = "//div[@class='objects new-object']//button[text()='Сохранить']")
    public WebElement buttonSaveOwnerBlock;
    @FindBy (xpath = "//div[@class='objects new-object']//p[@class='submit-form-info']")
    public WebElement successSaveOwnerBlockMessage;
    @FindBy (xpath = "//div[@class='chooseFromList-wrap']")
    public List<WebElement> svgLogoList;

}
