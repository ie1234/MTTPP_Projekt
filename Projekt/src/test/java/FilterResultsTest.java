import org.testng.Assert;
import org.testng.annotations.Test;
import pages.F1Page;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FilterResultsTest extends BaseTest {

    @Test
    public void testChangeResultsYear() {
        driver.get("https://www.formula1.com/");
        F1Page f1Page = new F1Page(driver);

        f1Page.acceptCookies();
        f1Page.clickResultsNavigation();

        wait.until(ExpectedConditions.urlContains("results"));

        f1Page.selectYearFromResults("2021");

        try { Thread.sleep(7500); } catch (InterruptedException e) {}

        Assert.assertTrue(driver.getCurrentUrl().contains("2021"),
                "URL ne sadrži 2021! Trenutni URL je: " + driver.getCurrentUrl());
    }
}