package CustomerEnd;

import base.ValidLogin;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.Locale;

public class Navbar extends ValidLogin {
    @Test(priority = 1, description = "Verify that user can click on the GCZBD Logo and navigate to the homepage")
    public void testNavbarIcon() {

        // Click on the GCZBD Logo & verify that user is navigated to the homepage successfully
        page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("Gift-Cards-Zone-BD")).first().click();
        String ActualPageTitle = page.title();
        System.out.println("Actual Page Title: " + ActualPageTitle);
        String ExpectedPageTitle = "Gift Cards, Games Top Up & More | Instant Delivery";
        if (ActualPageTitle.equals(ExpectedPageTitle)) {
            System.out.println("Test Passed: User is navigated to the homepage successfully & the page title is correct.");
        } else {
            System.out.println("Test Failed: User is not navigated to the homepage & the page title is incorrect.");
        }

        //Click on the Basket icon & verify that user is navigated to the Basket page successfully
        page.locator("button:nth-child(4)").first().click();
        String ActualPageTitle1 = page.title();
        System.out.println("Actual Page Title: " + ActualPageTitle1);
        String ExpectedPageTitle1 = "Gift Cards, Games Top Up & More | Instant Delivery - GCZBD";
        if (ActualPageTitle1.equals(ExpectedPageTitle1)) {
            System.out.println("Test Passed: User is navigated to the Basket page successfully & the page title is correct.");
        } else {
            System.out.println("Test Failed: User is not navigated to the Basket page & the page title is incorrect.");
        }
        page.navigate("https://gift.algosoftbd.com/");

        //Click on the Favourite icon & verify that user is navigated to the Favourite page successfully
        page.locator("button:nth-child(3)").first().click();
        String ActualPageTitle2 = page.title();
        System.out.println("Actual Page Title: " + ActualPageTitle2);
        String ExpectedPageTitle2 = "Gift Cards, Games Top Up & More | Instant Delivery - GCZBD";
        if (ActualPageTitle2.equals(ExpectedPageTitle2)) {
            System.out.println("Test Passed: User is navigated to the Favourite page successfully & the page title is correct.");
        } else {
            System.out.println("Test Failed: User is not navigated to the Favourite page & the page title is incorrect.");
        }
        page.navigate("https://gift.algosoftbd.com/");

    }

    @Test(priority = 2, description = "Verify that user can search for a product using the search bar and navigate to the product page successfully")
    public void testSearchBar() {
        // Type a product name in the search bar & click on the search button
        Locator SearchBar=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search"));
        SearchBar.click();
        SearchBar.fill("new cap");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search").setExact(true)).click();
        page.navigate("https://gift.algosoftbd.com/");

        SearchBar.click();
        SearchBar.fill("testing");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New testing my catalog")).click();
        page.navigate("https://gift.algosoftbd.com/");

        SearchBar.click();
        SearchBar.fill("hello");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear search")).click();
    }
}