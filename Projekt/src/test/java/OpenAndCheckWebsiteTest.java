import org.testng.Assert;
import org.testng.annotations.Test;
import pages.F1Page;

public class OpenAndCheckWebsiteTest extends BaseTest {
    @Test
    public void openSiteTest() {
        driver.get("https://www.formula1.com/");
        F1Page f1Page = new F1Page(driver);
        f1Page.acceptCookies();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}