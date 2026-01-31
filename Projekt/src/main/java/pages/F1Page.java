package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;



public class F1Page {
    WebDriver driver;
    WebDriverWait wait;

    private By cookieIframe = By.xpath("//iframe[contains(@id, 'sp_message_iframe')]");
    private By cookieAcceptBtn = By.xpath("//*[@id='notice']/div[3]/button[2]");
    private By loginLink = By.xpath("//a[contains(@href, 'login') or contains(@title, 'Sign In')]");
    private By loginFallback = By.xpath("/html/body/div[2]/header/div[2]/div[2]/div/div/div[2]/span[3]/a[1]");
    private By resultsNavBtn = By.xpath("/html/body/div[2]/header/div[3]/div[1]/div/div/div[2]/div[2]/div/nav/span[2]/span[1]/a");
    private By seasonsDropdown = By.id("seasons-dropdown");

    public F1Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acceptCookies() {
        try {
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(cookieIframe));
            driver.switchTo().frame(iframe);

            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

            System.out.println("Cookies accepted!");
            driver.switchTo().defaultContent();
            Thread.sleep(2000);
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            System.out.println("Cookie banner skipped.");
        }
    }

    public void clickLoginIcon() {
        try {
            System.out.println("Waiting for navigation to stabilize...");
            Thread.sleep(2000);

            By loginLink = By.xpath("//a[contains(@href, 'login') or contains(@title, 'Sign In')]");
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
            element.click();
            System.out.println("Clicked Login icon using dynamic selector.");
        } catch (Exception e) {
            System.out.println("Dynamic selector failed, trying absolute XPath...");
            driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/div[2]/div/div/div[2]/span[3]/a[1]")).click();
        }
    }

    public void enterLoginCredentials(String email, String password) {
        System.out.println("Starting login flow...");

        wait.until(ExpectedConditions.urlContains("account"));

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='loginform']/div[2]/input")
        ));

        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
        System.out.println("Email entered.");

        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
        System.out.println("Password entered.");

        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        loginBtn.click();
    }

    public void navigateToF1TV() {
        System.out.println("Navigating to F1 TV section...");

        WebElement f1TvLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'f1tv.formula1.com')]")
        ));
        f1TvLink.click();
    }

    public void clickResultsNavigation() {
        System.out.println("Attempting to click Results");

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        By resultsLocator = By.xpath("/html/body/div[2]/header/div[3]/div[1]/div/div/div[2]/div[2]/div/nav/span[2]/span[1]/a");

        try {
            WebElement resultsLink = wait.until(ExpectedConditions.elementToBeClickable(resultsLocator));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", resultsLink);

            System.out.println("Successfully clicked Results (Full XPath.)");
        } catch (Exception e) {
            System.out.println("Full XPath failed, trying backup direct navigation...");
            driver.get("https://www.formula1.com/en/results/races.html");
        }
    }

    public void selectYearFromResults(String year) {
        System.out.println("Opening year dropdown...");

        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("seasons-dropdown")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

            Thread.sleep(1500);

            By simpleYearLocator = By.xpath("//a[normalize-space()='" + year + "']");

            WebElement yearOption = wait.until(ExpectedConditions.visibilityOfElementLocated(simpleYearLocator));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yearOption);

            System.out.println("Year " + year + " clicked successfully!");

        } catch (Exception e) {
            System.out.println("Dropdown interaction failed, using direct link as fallback.");
            driver.get("https://www.formula1.com/en/results/races/" + year + "/races.html");
        }
    }

    public void changeDisplayMode(String mode) {
        System.out.println("Scrolling to footer to change display mode...");

        try {
            WebElement displayModeBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class, 'f1-display-mode')] | //button[contains(., 'Display mode')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", displayModeBtn);
            Thread.sleep(1000);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", displayModeBtn);
            System.out.println("Display mode dropdown opened.");

            Thread.sleep(1000);

            By modeOption = By.xpath("//div[@role='menu']//button[contains(., '" + mode + "')] | //span[contains(text(), '" + mode + "')]/parent::button");

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(modeOption));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

            System.out.println("Display mode '" + mode + "' clicked.");

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", displayModeBtn);

        } catch (Exception e) {
            System.out.println("Error changing display mode: " + e.getMessage());
            throw new RuntimeException("Could not change display mode to " + mode);
        }
    }

    public String getDisplayModeClass() {
        return driver.findElement(By.tagName("html")).getAttribute("class");
    }

}