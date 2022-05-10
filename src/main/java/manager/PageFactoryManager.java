package manager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactoryManager {
    WebDriver driver;

    public PageFactoryManager(WebDriver driver){
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }
    public BagPage getBagPage(){
        return new BagPage(driver);
    }
    public SearchPage getSearchPage(){
        return new SearchPage(driver);
    }
    public ProductPage getProductPage(){
        return new ProductPage(driver);
    }
    public LogInPage getLogInPage(){
        return new LogInPage(driver);
    }
    public InstagramPage getInstagramPage(){
        return new InstagramPage(driver);
    }
}
