package CustomerEnd;

import base.ValidLogin;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class ShopMenu extends ValidLogin {

    @Test(priority = 1, description = "Filtering by Region")
    public void filterByRegion() {
        // Code to filter products by region
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("SHOP")).click();
        page.navigate("https://gift.algosoftbd.com/shop?");

        String ActualPageTitle = page.title();
        System.out.println("Actual Page Title: " + ActualPageTitle);
        String ExpectedPageTitle = "Gift Cards, Games Top Up & More | Instant Delivery - GCZBD";
        if (ActualPageTitle.equals(ExpectedPageTitle)) {
            System.out.println("Page title verification passed.");
        } else {
            System.out.println("Page title verification failed.");
        }
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("🌍 All")).click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("United States")).click();
        page.navigate("https://gift.algosoftbd.com/shop?region=United%20States&page=1");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("US")).click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Bangladesh")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("BD")).click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("India")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("IN")).click();
    }

    @Test(priority = 2, description = "Filtering by Sorting")
    public void filterBySorting() {
        // Code to filter products by Sorting
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("SHOP")).click();

        String ActualPageTitle = page.title();
        System.out.println("Actual Page Title: " + ActualPageTitle);
        String ExpectedPageTitle = "Gift Cards, Games Top Up & More | Instant Delivery - GCZBD";
        if (ActualPageTitle.equals(ExpectedPageTitle)) {
            System.out.println("Page title verification passed.");
        } else {
            System.out.println("Page title verification failed.");
        }

        Locator SortBy = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sort by"));
        SortBy.click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Name: A to Z")).click();
        page.navigate("https://gift.algosoftbd.com/shop?sort_by=title&sort_order=asc&page=1");

        SortBy.click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Name: Z to A")).click();
        page.navigate("https://gift.algosoftbd.com/shop?sort_by=title&sort_order=desc&page=1");

        SortBy.click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Newest First")).click();
    }

    @Test(priority = 3, description = "Verify that user use Pagination")
    public void pagination() {
        // Code to verify pagination functionality
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("SHOP")).click();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next")).click();
        page.navigate("https://gift.algosoftbd.com/shop?sort_by=createdAt&sort_order=desc&page=4&count=16");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next")).click();
        page.navigate("https://gift.algosoftbd.com/shop?sort_by=createdAt&sort_order=desc&page=5&count=16");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next")).click();
        page.navigate("https://gift.algosoftbd.com/shop?sort_by=createdAt&sort_order=desc&page=6&count=16");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("3")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Prev")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("1")).click();
    }

    @Test(priority = 4,description = "Verify that user can add to favorite and remove from favorite")
    public void addToFavoriteAndRemoveFromFavorite() {
        // Code to verify adding and removing products from favorites
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("SHOP")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Rectangle 3.png Testing gift")).click();

        String ActualPageTitle = page.title();
        System.out.println("Actual Page Title: " + ActualPageTitle);
        String ExpectedPageTitle= "Testing gift subscription";
        if (ActualPageTitle.equals(ExpectedPageTitle)) {
            System.out.println("Page title verification passed.");
        } else {
            System.out.println("Page title verification failed.");
        }

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to favorite")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove from favorite")).click();
    }

    @Test(priority = 5,description = "Verify that user can buy a product")
    public void buyAProduct() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("SHOP")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Rectangle 3.png Testing gift")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Buy")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes, I do agree")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Checkout")).click();
        page.navigate("https://gift.algosoftbd.com/payment?order=GCZ570");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Pay now")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("bKash")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Transaction ID")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Transaction ID")).fill("12345thw");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Phone Number")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Phone Number")).fill("01436789036");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("VERIFY")).click();
        page.navigate("https://gift.algosoftbd.com/my-order?payment_status=success&payment_text=Payment%20successful");
    }
}

