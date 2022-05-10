package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import pages.*;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefinitionSteps {
    private static final long DEFAULT_TIMEOUT = 60;
    private static final String ERROR_SEARCH_MESSAGE = "NOTHING MATCHES YOUR SEARCH";
    private static final String ERROR_LOGIN_MESSAGE = "Email fail! Please type in your correct email address";
    private static final String ERROR_LOGIN_OR_PASSWORD_MESSAGE = "Looks like either your email address or password were incorrect. Wanna try again?";

    private int countOfViewedItemsBeforeLoadingMore = 0;
    private int countOfSearchItemsBeforeFiltering = 0;
    private String countOfSearchItemsWithDiscount = "";
    private String originalWindowID = "";
    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    BagPage bagPage;
    HomePage homePage;
    SearchPage searchPage;
    ProductPage productPage;
    LogInPage logInPage;
    InstagramPage instagramPage;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }
    @After
    public void tearDown() {
        //driver.close();
        driver.quit();
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @When("User makes search by keyword {string}")
    public void userMakesSearchByKeywordKeyWord(final String keyword) {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        homePage.enterInput(homePage.getSearchInput(), keyword);
    }

    @And("User clicks on the 'Search' button")
    public void userClicksOnTheSearchButton() {
        homePage.clickTheWebElement(homePage.getSearchButton());
    }

    @And("User scrolls to {int} product")
    public void userScrollsToIndexProduct(final int index)  {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        searchPage.scrollToElement(searchPage.getSearchElementByIndex(index), DEFAULT_TIMEOUT);
    }

    @And("User clicks {int} product")
    public void userClicksIndexProduct(final int index) {
        searchPage.moveToElementWithWait(searchPage.getSearchElementByIndex(index),DEFAULT_TIMEOUT);
        searchPage.clickTheWebElement(searchPage.getSearchElementByIndex(index));
        productPage = pageFactoryManager.getProductPage();
    }

    @And("User clicks 'Add to Bag' button")
    public void userClicksAddToBagButton() {
        productPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        productPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        productPage.clickTheWebElement(productPage.getAddToBagButton());
    }

    @And("User moves to cart button")
    public void userMovesToCartButton() {
        productPage.moveToElementWithWait(productPage.getBagDropDownElement(), DEFAULT_TIMEOUT);
    }

    @And("User clicks 'View Bag' button")
    public void userClicksViewBagButton() {
        productPage.moveToElementWithWait(productPage.getViewBagButton(), DEFAULT_TIMEOUT);
        productPage.clickTheWebElement(productPage.getViewBagButton());
    }

    @Then("User checks that product is on the bag page")
    public void userChecksThatProductIsOnTheBagPage() {
        bagPage = pageFactoryManager.getBagPage();
        bagPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        bagPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);

        assertTrue(bagPage.isElementDisplayed(bagPage.getItemInBag()));
    }

    @Then("User checks that count of products in cart matches the number of added products")
    public void userChecksThatCountOfProductsInCartIs() {
        productPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        productPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        productPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productPage.getCountOfProductsInCartButton());
        assertEquals(productPage.getIntCountOfProductsInCartButton(), 1);
    }

    @Then("User checks that search result is empty")
    public void userChecksThatSearchResultIsEmpty() {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        assertTrue(searchPage.getTextErrorSearchMessage().contains(ERROR_SEARCH_MESSAGE));
    }

    @And("User moves to 'Sign In' button")
    public void userMovesToSignInButton() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        homePage.moveToElementWithWait(homePage.getAccountButton(),DEFAULT_TIMEOUT);
    }

    @And("User clicks 'Sign In' button")
    public void userClicksSignInButton() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getSignInLink());
        homePage.clickTheWebElement(homePage.getSignInLink());
    }

    @When("User enter invalid {string} and {string}")
    public void userEnterInvalidLoginAndPassword(final String login, final String password) {
        logInPage = pageFactoryManager.getLogInPage();
        logInPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        logInPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        logInPage.enterInput(logInPage.getLoginInput(), login);
        logInPage.enterInput(logInPage.getPasswordInput(), password);
    }

    @And("User clicks 'Sign In' button on 'Sign in' page")
    public void userClicksSignInButtonOnSignInPage() {
        logInPage.clickTheWebElement(logInPage.getSignInButton());
    }

    @Then("User checks error message upper login input")
    public void userChecksErrorMessageUpperLoginInput() {
        logInPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, logInPage.getErrorLoginMessage());
        assertTrue(logInPage.getTextErrorLoginMessage().contains(ERROR_LOGIN_MESSAGE));
    }

    @When("User enter valid {string} and invalid {string}")
    public void userEnterValidLoginAndInvalidPassword(final String login, final String password) {
        logInPage = pageFactoryManager.getLogInPage();
        logInPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        logInPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        logInPage.enterInput(logInPage.getLoginInput(), login);
        logInPage.enterInput(logInPage.getPasswordInput(), password);
    }

    @Then("User checks error message of email address or password are incorrect")
    public void userChecksErrorMessageOfEmailAddressOrPasswordAreIncorrect() {
        logInPage = pageFactoryManager.getLogInPage();
        logInPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        logInPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        assertTrue(logInPage.getTextErrorLoginOrPasswordMessage().contains(ERROR_LOGIN_OR_PASSWORD_MESSAGE));
    }

    @And("User scrolls to 'Load More' button")
    public void userScrollsToLoadMoreButton() {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        searchPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchPage.getLoadMoreButton());
        searchPage.scrollToElement(searchPage.getLoadMoreButton(), DEFAULT_TIMEOUT);
    }

    @And("User clicks to 'Load More' button")
    public void userClicksToLoadMoreButton() {
        searchPage.clickTheWebElement(searchPage.getLoadMoreButton());
    }

    @And("User checks how many items are displayed on the page")
    public void userChecksHowManyItemsAreDisplayedOnThePage() {
        countOfViewedItemsBeforeLoadingMore = searchPage.getIntCountOfViewedProducts();
    }

    @Then("User checks that count of viewed items increased")
    public void userChecksThatCountOfViewedItemsIncreased() {
        assertTrue(searchPage.getIntCountOfViewedProducts() > countOfViewedItemsBeforeLoadingMore);
    }

    @When("User click the 'Discount %' button")
    public void userClickTheDiscountButton() {
        searchPage.clickTheWebElement(searchPage.getDiscountDropDownList());
    }

    @And("User checks count of search elements")
    public void userChecksCountOfSearchElements() {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        countOfSearchItemsBeforeFiltering = searchPage.getIntCountOfSearchProducts();
    }

    @And("User click the {int} discount selection")
    public void userClickTheIndexDiscountSelection(final int index) {
        searchPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchPage.getDiscountPercentageElementByIndex(index));
        countOfSearchItemsWithDiscount = searchPage.getStringCountOfProductWithDiscountByIndex(index);
        countOfSearchItemsWithDiscount += " styles found";
        searchPage.clickTheWebElement(searchPage.getDiscountPercentageElementByIndex(index));
    }

    @Then("User checks that count of found products decreased")
    public void userChecksThatCountOfFoundProductsDecreasedAfterClickingIndexDiscountSelection() {
        searchPage.waitForTextInElement(DEFAULT_TIMEOUT, searchPage.getCountOfSearchProducts(), countOfSearchItemsWithDiscount);
        assertTrue(searchPage.getIntCountOfSearchProducts() < countOfSearchItemsBeforeFiltering);
    }

    @And("User moves to 'Sales' category")
    public void userMovesToSalesCategory() {
        homePage.moveToElementWithWait(homePage.getSalesCategoryByIndex(1), DEFAULT_TIMEOUT);
    }

    @And("User moves to 'Sale bestsellers' button")
    public void userMovesToSaleBestsellersButton() {
        homePage.moveToElementWithWait(homePage.getSaleBestsellersCategoryByIndex(1), DEFAULT_TIMEOUT);
    }

    @When("User clicks 'Sale bestsellers' button")
    public void userClicksSaleBestsellersButton() {
        homePage.clickTheWebElement(homePage.getSaleBestsellersCategoryByIndex(1));
    }

    @Then("User checks header on the page, it must be {string}")
    public void userChecksHeaderOnThePageItMustBeHeader(final String header) {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        assertEquals(searchPage.getStringHeaderOfSearchPageCategory(), header);
    }

    @When("User clicks 'Men' page button")
    public void userClicksMenPageButton() {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        searchPage.clickTheWebElement(searchPage.getHomeMenPageButton());
    }

    @Then("User checks that opened {string}")
    public void userChecksThatOpenedHomePage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        assertEquals(homePage.getDriver().getCurrentUrl(), url);
    }

    @And("User scrolls to 'Instagram' button")
    public void userMovesToInstagramButton() {
        homePage.scrollToElement(homePage.getInstagramButton(), DEFAULT_TIMEOUT);
    }

    @When("User clicks 'Instagram' button")
    public void userClicksInstagramButton() {
        originalWindowID = homePage.getDriver().getWindowHandle();
        assertTrue(homePage.countOfOpenedTabs() == 1);
        homePage.clickTheWebElementByJS(homePage.getInstagramButton());
    }


    @Then("User checks that opened {string} in new tab")
    public void userChecksThatOpenedInstagramPageInNewTab(final String url) {
        instagramPage = pageFactoryManager.getInstagramPage();
        instagramPage.waitUntilCountOfTabsOpened(DEFAULT_TIMEOUT, 2);
        instagramPage.switchToNextTab(originalWindowID);
        assertEquals(instagramPage.getDriver().getCurrentUrl(), url);
    }
}
