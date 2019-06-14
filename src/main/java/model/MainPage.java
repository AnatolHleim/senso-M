package model;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {
    public static final String URL_MAIN = "http://31.130.206.73:3210/app";
    public MainPage(WebDriver driver, Logger log) {
        super(driver,log);
    }
@FindBy (xpath = "//div[@class='card card-add-obj']")
    WebElement addNewObject;
    @FindBy(xpath = "//div[@class='notFound__text']")
    public WebElement welcome;
    public NewObjectPage addNewObject (){
        click(addNewObject);
        return new NewObjectPage(driver,log);
    }
}
