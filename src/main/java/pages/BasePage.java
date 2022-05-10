package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void waitForPageLoadComplete(long timeToWait) {
        new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForAjaxToComplete(long timeToWait) {
        new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return window.jQuery != undefined && jQuery.active == 0;"));
    }

    public WebElement waitVisibilityOfElement(long timeToWait, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWait));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForTextInElement(long timeToWait, WebElement element, String text){
        new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(
                ExpectedConditions.textToBePresentInElement(element, text)
        );
    }

    public void waitUntilCountOfTabsOpened(long timeToWait, int count){
        new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(ExpectedConditions.numberOfWindowsToBe(count));
    }

    public void switchToNextTab(String originalWindow){
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void moveToElementWithWait(WebElement webElement, long waitTime){
        actions.moveToElement(waitVisibilityOfElement(waitTime, webElement)).perform();
    }

    public void scrollToElement(WebElement element, long timeToWait){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToTop(){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    public void clickTheWebElement(WebElement webElement){
        webElement.click();
    }
    public void clickTheWebElementByJS(WebElement webElement){
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("arguments[0].click();", webElement);
    }
    public void enterInput(WebElement webElement, String message){
        webElement.sendKeys(message);
    }
    public int countOfOpenedTabs(){
        return getDriver().getWindowHandles().size();
    }

    public boolean isElementDisplayed(WebElement webElement){
        return webElement.isDisplayed();
    }
}
