// 6. pages/LoginPage.java  (Page Factory + JS popup dismiss)
package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;
import utils.WaitUtils;

import java.time.Duration;

public class LoginPage {
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container h3")
    private WebElement errorMessage;

    public LoginPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
        System.out.println("LoginPage initialized with Page Factory");
    }

    public void login(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);
        System.out.println("Step: Entered username -> " + username);

        passwordField.clear();
        passwordField.sendKeys(password);
        System.out.println("Step: Entered password");

        loginButton.click();
        System.out.println("Step: Clicked Login button");

          // Simple JS to ignore Google Password Manager popup
    }


    public String getErrorMessage() {
        WaitUtils.waitForVisibility(errorMessage);
        String msg = errorMessage.getText();
        System.out.println("Step: Error message captured -> " + msg);
        return msg;
    }

    public boolean isProductsPageLoaded() {
        return DriverManager.getDriver().getCurrentUrl().contains("inventory.html");
    }
}