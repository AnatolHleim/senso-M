import controller.TestData;
import model.AuthPage;
import model.ResetPasswordPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResetPasswordPageTest extends TestData {
    @Test
    public void verifySend(){
        AuthPage page = new AuthPage(driver,log);
        page.openPage();
       ResetPasswordPage passwordPage =  page.goToResetPasswordPage();
       passwordPage.sendEmailToResetPassword("rest");
        Assert.assertEquals(passwordPage.find(passwordPage.errorNotAvailibleEmail).getText(), "Пользователя с таким адресом нет, проверьте написание");
    }
}
