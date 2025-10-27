import org.testng.Assert;
import org.testng.annotations.Test;
import page.MainPage;
import runner.BaseTest;

public class FormFieldsTest extends BaseTest {

    private static final String NAME = "Eldar";
    private static final String PASSWORD = "Ddfgdg53!";
    private static final String EMAIL = "braid2010@gmail.com";
    private static final String EXPECTED_ALERT_TEXT = "Message received!";
    private static final String EXPECTED_PLACEHOLDER_TEXT = "Enter message here";
    private static final String EXPECTED_REQUIRED_MESSAGE = "* Required";

    @Test
    public void testFormFields() {
        String alertText = new MainPage(getDriver())
                .inputName(NAME)
                .inputPassword(PASSWORD)
                .chooseMilk()
                .chooseCoffee()
                .chooseYellow()
                .selectOne()
                .inputEmail(EMAIL)
                .inputInstrumentCount()
                .clickSubmit()
                .getAlertText();

        Assert.assertEquals(alertText, EXPECTED_ALERT_TEXT);
    }

    @Test
    public void testPlaceHolderMessage() {
        String placeHolderText = new MainPage(getDriver())
                .getPlaceHolderText();

        Assert.assertEquals(placeHolderText, EXPECTED_PLACEHOLDER_TEXT);
    }

    @Test
    public void testRequiredMessage() {
        String requiredMessage = new MainPage(getDriver())
                .clickSubmit()
                .getRequiredMessage();

        Assert.assertEquals(requiredMessage, EXPECTED_REQUIRED_MESSAGE);
    }
}
