package runner;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.time.Duration;

public abstract class BaseTest {

    private RemoteWebDriver driver;
    private WebDriverWait wait10;

    protected RemoteWebDriver getDriver() {
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
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

            String selenoidUrl = System.getenv("SELENOID_URL");

            if (selenoidUrl == null || selenoidUrl.isEmpty()) {
                throw new IllegalStateException("SELENOID_URL is not set!");
            }

            driver = new RemoteWebDriver(new URL(selenoidUrl), options);

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
