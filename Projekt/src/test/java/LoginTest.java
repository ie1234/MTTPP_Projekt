import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.F1Page;

public class LoginTest extends BaseTest {

    @Test
    public void testInvalidLoginFlow() {
        driver.get("https://www.formula1.com/");
        F1Page f1Page = new F1Page(driver);

        f1Page.acceptCookies();

        f1Page.clickLoginIcon();

        f1Page.enterLoginCredentials("wrong_user@mttp.com", "InvalidPass123");

        try { Thread.sleep(4000); } catch (InterruptedException e) {}

        String pageContent = driver.getPageSource();
        boolean isErrorVisible = pageContent.contains("Invalid") || pageContent.contains("incorrect");

        Assert.assertTrue(isErrorVisible, "Error message was not displayed after invalid login!");
    }
}