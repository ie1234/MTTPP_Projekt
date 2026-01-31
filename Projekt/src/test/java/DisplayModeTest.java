import org.testng.Assert;
import org.testng.annotations.Test;
import pages.F1Page;

public class DisplayModeTest extends BaseTest {

    @Test
    public void testDisplayModeChange() throws InterruptedException {
        driver.get("https://www.formula1.com/");
        F1Page f1Page = new F1Page(driver);
        f1Page.acceptCookies();

        f1Page.changeDisplayMode("Dark");
        Thread.sleep(1500);
        String darkColor = f1Page.getDisplayModeClass();
        System.out.println("Dark mode color: " + darkColor);

        f1Page.changeDisplayMode("Light");
        Thread.sleep(2000);
        String lightColor = f1Page.getDisplayModeClass();
        System.out.println("Light mode color: " + lightColor);

        Assert.assertNotEquals(darkColor, lightColor, "Background color should change when mode is switched from Dark to Light.");
    }
}