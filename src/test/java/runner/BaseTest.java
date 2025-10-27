package runner;

import org.openqa.selenium.remote.DesiredCapabilities;
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
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("121.0");

            String selenoidUrl = System.getenv("SELENOID_URL");

            if (selenoidUrl == null || selenoidUrl.isEmpty()) {
                throw new IllegalStateException("SELENOID_URL is not set!");
            }

            driver = new RemoteWebDriver(new URL(selenoidUrl), capabilities);

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
