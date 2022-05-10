package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchInput;
    @FindBy(xpath = "//button[@data-testid='search-button-inline']")
    private WebElement searchButton;
    @FindBy(xpath = "//button[@data-testid='myAccountIcon']")
    private WebElement accountButton;
    @FindBy(xpath = "//a[@data-testid='signin-link']")
    private WebElement signInLink;
    @FindBy(xpath = "//nav[contains(@aria-label, 'Men')]//span[text()='Sale']")
    private List<WebElement> salesCategoryList;
    @FindBy(xpath = "//span[text()='Sale Bestsellers']")
    private List<WebElement> saleBestsellersCategoryList;
    @FindBy(xpath = "//ul[@data-testid='social-links-bar']//span[text()='Instagram']")
    private WebElement instagramButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSearchInput(){
        return searchInput;
    }
    public WebElement getSearchButton(){
        return searchButton;
    }
    public WebElement getAccountButton(){
        return accountButton;
    }
    public WebElement getSignInLink(){
        return signInLink;
    }

    public List<WebElement> getSalesCategoryList(){
        return salesCategoryList;
    }
    public WebElement getSalesCategoryByIndex(int index){
        return getSalesCategoryList().get(index);
    }
    public List<WebElement> getSaleBestsellersCategoryList(){
        return saleBestsellersCategoryList;
    }
    public WebElement getSaleBestsellersCategoryByIndex(int index){
        return getSaleBestsellersCategoryList().get(index);
    }
    public WebElement getInstagramButton(){
        return instagramButton;
    }

    public void openHomePage(String url) {
        driver.get(url);
    }
}
