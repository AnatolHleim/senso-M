import controller.TestData;

import model.NewObjectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NewObjectGeneralTest extends TestData{
    @Test
    public void one(){
        driver.get("http://localhost:4200/");
        WebElement ownerInput =driver.findElement(By.xpath("//div[@class='input datalist' and preceding-sibling::div/h2[text()='Владелец процесса']]"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ownerInput.click();
        WebElement input = ownerInput.findElement(By.xpath("./input"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        input.sendKeys("2");
        List<WebElement> listSelectOwner = ownerInput.findElements(By.xpath("./datalist/option"));

        for (WebElement e:listSelectOwner
        ) {
            System.out.println(e.getAttribute("value"));
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
