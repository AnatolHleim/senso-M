import controller.TestData;

import model.NewObjectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NewObjectGeneralTest extends TestData{
    @Test(description = "newObject")
    public void newObject(){


        NewObjectPage newObjectPage = new NewObjectPage(driver, log);


        newObjectPage.openPage();
            driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
            newObjectPage.click(newObjectPage.ownerInput);
//        WebElement my_dropdown = driver.findElement(By.xpath("//*[@id=\"names\"]"));
//
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@class='input datalist']//option[1]")).click();
      // Select select = new (driver.findElement(By.xpath("//*[@id=\"names\"]")));
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
      //  select.selectByVisibleText("Сергей Сергеевич Иванов 2");
        //driver.findElement(By.xpath("//*[@id=\"system-main\"]/div/table/tbody/tr[1]/td[2]/select/option[3]")).click();
           //newObjectPage.click(driver.findElement(By.xpath("//*[@id=\"names\"]")));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //   newObjectPage.type("fake",newObjectPage.ownerInput);

        Assert.assertTrue(true);

    }
}
