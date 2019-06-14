import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import controller.TestData;
import model.AuthPage;
import model.ResetPasswordPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ResetPasswordPageTest extends TestData {
    @Test (dataProvider = "getDataResetPassword", description = "ResetPasswordTest")
    public void verifySend(TestData data){
        AuthPage page = new AuthPage(driver,log);
        page.openPage();
       ResetPasswordPage passwordPage =  page.goToResetPasswordPage();
       passwordPage.sendEmailToResetPassword(data.getUsername());
        Assert.assertTrue(passwordPage.findBy(By.xpath(data.getMessageText())).isDisplayed());
    }
    @DataProvider
    public Object[][] getDataResetPassword() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/resetPasswordData.json"));
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
