package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {

    @FindBy(id = "name-input")
    private WebElement nameField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@value='Milk']")
    private WebElement milk;

    @FindBy(xpath = "//input[@value='Coffee']")
    private WebElement coffee;

    @FindBy(xpath = "//label[@for='color3']")
    private WebElement yellow;

    @FindBy(id = "automation")
    private WebElement selector;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    @FindBy(xpath = "//form/ul/li")
    private List<WebElement> instruments;

    @FindBy(xpath = "//textarea[@id='message']")
    private WebElement massageField;

    @FindBy(id = "submit-btn")
    private WebElement button;

    @FindBy(id = "message")
    private WebElement placeholderMessage;

    @FindBy(className = "red_txt")
    private WebElement requiredMessage;

    private final RemoteWebDriver driver;
    private WebDriverWait wait10;

    public MainPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://practice-automation.com/form-fields/");
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait10;
    }

    public MainPage inputName(String name) {
        nameField.sendKeys(name);
        return this;
    }

    public MainPage inputPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public MainPage chooseMilk() {
        milk.click();
        return this;
    }

    public MainPage chooseCoffee() {
        coffee.click();
        return this;
    }

    public MainPage chooseYellow() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='color3']")));
        yellow.click();
        return this;
    }

    public MainPage selectOne() {
        Select select = new Select(selector);
        select.selectByIndex(1);
        return this;
    }

    public MainPage inputEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public MainPage inputInstrumentCount() {
        List<WebElement> instrumentsList = instruments;

        int maxLenghtSymbols = instrumentsList.stream()
                .map(WebElement::getText)
                .mapToInt(String::length)
                .max()
                .orElse(0);

        massageField.sendKeys("Кол-во инструментов: " + String.valueOf(instrumentsList.size())
                + "\n" + "Наибольшее кол-во символов названия инструмента: " + maxLenghtSymbols);
        return this;
    }

    public MainPage clickSubmit() {
        Actions actions = new Actions(getDriver());
        actions
                .moveToElement(button)
                .click()
                .perform();
        return this;
    }

    public String getAlertText() {
        getWait10().until(ExpectedConditions.alertIsPresent());
        return getDriver().switchTo().alert().getText();
    }

    public String getPlaceHolderText() {
        return placeholderMessage.getAttribute("placeholder");
    }

    public String getRequiredMessage() {
        return requiredMessage.getText();
    }
}
