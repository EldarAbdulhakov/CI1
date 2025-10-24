package runner;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.time.Duration;

public abstract class BaseTest {

    private WebDriver driver;
    private WebDriverWait wait10;

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait10;
    }

    @BeforeMethod
    public void setUp() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            String selenoidUrl = System.getenv("SELENOID_URL");
            driver = new RemoteWebDriver(new URL(selenoidUrl), options);
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    protected void closeDriver() {
        if (driver != null) {
            driver.quit();
            wait10 = null;
        }

    }
}
