import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import controller.TestData;

import model.NewObjectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
    @Test(dataProvider = "getDataNewObject" )
    public void inputDataFirstBlock(TestData data){
        NewObjectPage newObjectPage = new NewObjectPage(driver,log).openPage();
        newObjectPage.type(data.getOwnerName(),newObjectPage.ownerField.findElement(By.xpath("./input")));
        newObjectPage.type(data.getEngineerName(),newObjectPage.firstEngeneerField.findElement(By.xpath("./input")));
        newObjectPage.type(data.getManagerName(),newObjectPage.managerField.findElement(By.xpath("./input")));
        newObjectPage.type(data.getObjectName(),newObjectPage.nameObjectInput);
        newObjectPage.type(data.getObjectAddress(),newObjectPage.adressObjectInput);
        newObjectPage.type(data.getObjectURL(),newObjectPage.URLObjectInput);
        newObjectPage.type(data.getUsername(),newObjectPage.loginInput);
        newObjectPage.type(data.getPassword(),newObjectPage.passwordInput);
        newObjectPage.click(newObjectPage.checkBoxAuth);
        newObjectPage.click(newObjectPage.buttonLoadStandartLogo);
        newObjectPage.click(newObjectPage.svgLogoList.get(0));
        newObjectPage.type(data.getSnowCover(),newObjectPage.snowCoverInput);
        newObjectPage.click(newObjectPage.buttonSaveOwnerBlock);
        Assert.assertEquals(newObjectPage.findXpath(data.getLocator()).getText(),data.getMessageText());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @DataProvider
    public Object[][] getDataNewObject() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/newObjectData.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<TestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestData>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}
