import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.F1Page;

public class F1TVTest extends BaseTest {

    @Test
    public void testF1TVPageLoad() {
        driver.get("https://www.formula1.com/");
        F1Page f1Page = new F1Page(driver);

        f1Page.acceptCookies();

        f1Page.navigateToF1TV();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        System.out.println("Current F1 TV URL: " + driver.getCurrentUrl());

        Assert.assertTrue(driver.getCurrentUrl().contains("f1tv"), "The URL does not contain 'f1tv'!");

        boolean isTitleCorrect = driver.getTitle().contains("F1 TV");
        Assert.assertTrue(isTitleCorrect, "F1 TV Page title is incorrect!");

        try {
            System.out.println("Waiting 5 seconds so you can see the F1 TV page...");
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}