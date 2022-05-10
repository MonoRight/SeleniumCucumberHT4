package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.lang.Character.isDigit;

public class SearchPage extends BasePage{

    @FindBy(xpath="//div[@class='ERlP6Bx']")
    private List<WebElement> searchElementList;
    @FindBy(xpath = "//h2[contains(text(), 'NOTHING')]")
    private WebElement errorSearchMessage;
    @FindBy(xpath = "//a[@data-auto-id='loadMoreProducts']")
    private WebElement loadMoreButton;
    @FindBy(xpath = "//p[@data-auto-id='productsProgressBar']")
    private WebElement countOfViewedProducts;
    @FindBy(xpath = "//li[@data-dropdown-id='discount_band']//div[contains(text(),'Discount')]")
    private WebElement discountDropDownList;
    @FindBy(xpath = "//p[@data-auto-id='styleCount']")
    private WebElement countOfSearchProducts;
    @FindBy(xpath = "//li[@data-dropdown-id=\"discount_band\"]//li")
    private List<WebElement> discountsPercentageList;
    @FindBy(xpath = "//span[@data-auto-id=\"refinementItemCount\"]")
    private List<WebElement> countOfProductsWithDiscountList;
    @FindBy(xpath = "//section[@id='category-banner-wrapper']//h1")
    private WebElement headerOfSearchPageCategory;
    @FindBy(xpath = "//nav[@aria-label='breadcrumbs']//a[contains(text(), 'Men')]")
    private WebElement homeMenPageButton;
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getSearchElementList(){
        return searchElementList;
    }

    public WebElement getSearchElementByIndex(int index){
        return getSearchElementList().get(index);
    }
    public WebElement getErrorSearchMessage(){
        return errorSearchMessage;
    }

    public String getTextErrorSearchMessage(){
        return errorSearchMessage.getText();
    }
    public WebElement getLoadMoreButton(){
        return loadMoreButton;
    }

    public WebElement getCountOfViewedProducts(){
        return countOfViewedProducts;
    }

    public int getIntCountOfViewedProducts(){
        String count = "";
        int countOfSpaces = 0;
        char[] charsOfText = getCountOfViewedProducts().getText().toCharArray();
        for (char ch: charsOfText) {
            if(isDigit(ch) && countOfSpaces < 3){
                count += ch;
            }
            if(ch == ' '){
                countOfSpaces++;
            }
            if(countOfSpaces >= 3){
                break;
            }
        }
        return Integer.parseInt(count);
    }
    public WebElement getDiscountDropDownList(){
        return discountDropDownList;
    }
    public WebElement getCountOfSearchProducts(){
        return countOfSearchProducts;
    }
    public int getIntCountOfSearchProducts(){
        return Integer.parseInt(getCountOfSearchProducts().getText().replaceAll("\\D+",""));
    }

    public List<WebElement> getDiscountsPercentageList(){
        return discountsPercentageList;
    }
    public WebElement getDiscountPercentageElementByIndex(int index){
        return getDiscountsPercentageList().get(index);
    }

    public List<WebElement> getCountOfProductsWithDiscountList(){
        return countOfProductsWithDiscountList;
    }

    public WebElement getCountOfProductWithDiscountByIndex(int index){
        return getCountOfProductsWithDiscountList().get(index);
    }

    public String getStringCountOfProductWithDiscountByIndex(int index){
        return getCountOfProductWithDiscountByIndex(index).getText().replace(")","").replace("(","");
    }

    public WebElement getHeaderOfSearchPageCategory(){
        return headerOfSearchPageCategory;
    }

    public String getStringHeaderOfSearchPageCategory(){
        return getHeaderOfSearchPageCategory().getText();
    }
    public WebElement getHomeMenPageButton(){
        return homeMenPageButton;
    }

}
