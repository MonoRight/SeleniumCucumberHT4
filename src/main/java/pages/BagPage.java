package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BagPage extends BasePage{
    @FindBy(xpath="//li[@class='bag-item-holder']")
    private WebElement itemInBag;
    public BagPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getItemInBag(){
        return itemInBag;
    }

}
