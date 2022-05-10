package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage{
    @FindBy(xpath = "//input[@name='Username']")
    private WebElement loginInput;
    @FindBy(xpath = "//input[@name='Password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//span[@id='EmailAddress-error']")
    private WebElement errorLoginMessage;
    @FindBy(xpath = "//input[@id='signin']")
    private WebElement signInButton;
    @FindBy(xpath = "//li[@id='loginErrorMessage']")
    private WebElement errorLoginOrPasswordMessage;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getLoginInput(){
        return loginInput;
    }
    public WebElement getPasswordInput(){
        return passwordInput;
    }
    public WebElement getErrorLoginMessage(){
        return errorLoginMessage;
    }
    public String getTextErrorLoginMessage(){
        return getErrorLoginMessage().getText();
    }
    public WebElement getSignInButton(){
        return signInButton;
    }
    public WebElement getErrorLoginOrPasswordMessage(){
        return errorLoginOrPasswordMessage;
    }
    public String getTextErrorLoginOrPasswordMessage(){
        return getErrorLoginOrPasswordMessage().getText();
    }

}
