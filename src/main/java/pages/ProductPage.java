package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

    @FindBy(xpath = "//div[@class='add-item']//button")
    private WebElement addToBagButton;
    @FindBy(xpath = "//div[@id='miniBagDropdown']")
    private WebElement bagDropDownElement;
    @FindBy(xpath = "//a[@data-test-id='bag-link']")
    private WebElement viewBagButton;
    @FindBy(xpath = "//span[@data-test-id='miniBagItemCount']")
    private WebElement countOfProductsInCartButton;
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAddToBagButton(){
        return addToBagButton;
    }
    public WebElement getBagDropDownElement(){
        return bagDropDownElement;
    }
    public WebElement getViewBagButton(){
        return viewBagButton;
    }
    public WebElement getCountOfProductsInCartButton(){
        return countOfProductsInCartButton;
    }
    public int getIntCountOfProductsInCartButton(){
        return Integer.parseInt(getCountOfProductsInCartButton().getText().replaceAll("\\D+",""));
    }
}
