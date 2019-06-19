import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import controller.TestData;
import model.AuthPage;
import model.MainPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class AuthPageTestPositive extends TestData {
    @Test
    public void verifyTitlePage(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        Assert.assertEquals(authPage.getCurrentPageTitle(),"Client");
    }
    @Test
    public void verifyCurrentUrlPage(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        Assert.assertEquals(authPage.getCurrentUrl(),AuthPage.URL_AUTH);
    }
    @Test
    public void verifyShowPasswordOnClickEye(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        authPage.type("some", authPage.inputPassword);
        authPage.click(authPage.buttonShowPassword);
        Assert.assertTrue(authPage.elementHasAttribute(authPage.inputPassword,"type","text"));
    }
    @Test
    public void verifyInvisiblePasswordOnDoubleClickEye(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        authPage.type("some", authPage.inputPassword);
        authPage.click(authPage.buttonShowPassword);
        authPage.click(authPage.buttonShowPassword);
        Assert.assertTrue(authPage.elementHasAttribute(authPage.inputPassword,"type","password"));
    }
    @Test
    public void verifyShowModalWindowRequisites(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        authPage.click(authPage.linkFooterRequisites);
        Assert.assertTrue(authPage.frameModalRequisites.isDisplayed());
    }
    @Test
    public void verifyCloseModalWindowRequisites(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        authPage.click(authPage.linkFooterRequisites);
        authPage.click(authPage.buttonCloseModalRequisites);
        Assert.assertTrue(authPage.elementHasAttribute(authPage.frameModalRequisites,"class","requisites-bg requisites_hidden"));
    }
    @Test
    public void jsErrorTest() {
        log.info("Starting jsErrorTest");
        AuthPage authPage = new AuthPage(driver, log).openPage();
        authPage.type("asd@de.re",authPage.inputUserEmail);
        authPage.type("asd@de.re",authPage.inputPassword);
        authPage.click(authPage.buttonSubmit);
    }
    @Test(dataProvider = "getDataPositive", description = "PositiveAuthTest")
    public void positiveAuthTest(TestData data){

        log.info(data.getTestCase());
        AuthPage authPage = new AuthPage(driver, log).openPage();
        MainPage mainPage = authPage.logIn(data.getUsername(),data.getPassword());
        Assert.assertEquals(data.getMessageText(), mainPage.find(mainPage.welcome).getText());

    }
    @DataProvider
    public Object[][] getDataPositive() throws FileNotFoundException {
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
