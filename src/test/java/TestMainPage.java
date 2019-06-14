import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import controller.BaseTest;
import controller.TestData;
import model.AuthPage;
import model.MainPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TestMainPage extends BaseTest {
    @Test(dataProvider = "getData")
    public void verifyCurrentUrl(TestData data){
    AuthPage authPage = new AuthPage(driver, log);
    authPage.openPage();
    authPage.logIn(data.getUsername(),data.getPassword());
    Assert.assertEquals(authPage.getCurrentUrl(), MainPage.URL_MAIN);
}
    @Test(dataProvider = "getData")
public void newObjectPageOpen(TestData data){
    AuthPage authPage = new AuthPage(driver, log);
    authPage.openPage();
    authPage.logIn(data.getUsername(),data.getPassword())
            .addNewObject();
    Assert.assertEquals("http://31.130.206.73:3210/app/addObject", authPage.getCurrentUrl());

}

    @DataProvider
    public Object[][] getData() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/positiveAuthData.json"));
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
