package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;
import utils.WaitUtils;

public class LoginPage {
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public String getErrorMessage() {
        WaitUtils.waitForElementVisible(errorMessage, 5);
        return errorMessage.getText();
    }

    public boolean isErrorDisplayed() {
        return errorMessage.isDisplayed();
    }
}
