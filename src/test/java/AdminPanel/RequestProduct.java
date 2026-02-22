package AdminPanel;

import base.BaseTest;
import com.beust.ah.A;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class RequestProduct extends BaseTest {

    @Test(priority = 1, description = "Verify that admin can view request product details")
    public void verifyAdminCanViewRequestProductDetails() {

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Req Product")).click();
        page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(" View GCZRP46 ")).locator("a").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK")).click();

        String ExpectedPageTitle = page.title();
        String ActualPageTitle = "Req Product | Gift Cards Zone BD";
        System.out.println("Expected Page Title: " + ExpectedPageTitle);
        System.out.println("Actual Page Title: " + ActualPageTitle);

        if (ExpectedPageTitle.equals(ActualPageTitle)) {
            System.out.println("Actual page title matches expected page title. Test Passed.");
        } else {
            System.out.println("Actual page title does not match expected page title. Test Failed.");
        }
    }

    @Test(priority = 2,description = "Verify that admin can can change request product status")
    public void verifyAdminCanChangeRequestProductStatus() {

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Req Product")).click();
        Locator View=page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(" View GCZRP46 ")).getByRole(AriaRole.BUTTON);
        View.click();
        page.getByRole(AriaRole.TOOLTIP).getByText("Pending").click();
        Locator YesButton=page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes, do it"));
        YesButton.click();

        View.click();
        page.getByRole(AriaRole.TOOLTIP).getByText("Added").click();
        YesButton.click();

        View.click();
        page.getByRole(AriaRole.TOOLTIP).getByText("Rejected").click();
        YesButton.click();
    }

    @Test(priority = 3,description = "Verify that admin can can delete request product")
    public void verifyAdminCanDeleteRequestProduct() {

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Req Product")).click();
        Locator View=page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(" View GCZRP42 ")).getByRole(AriaRole.BUTTON);
        View.click();
        page.getByRole(AriaRole.TOOLTIP).getByText("Delete").click();
        Locator YesButton=page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes, do it"));
        YesButton.click();
    }

    @Test(priority = 4,description = "Verify that admin can filter and search request product based on different criteria")
    public void verifyAdminCanSearchByIdx() {

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Req Product")).click();
        Locator SearchByIdx=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search by product name or idx"));
        SearchByIdx.click();
        SearchByIdx.fill("46");
        Locator ClearFilters=page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)"));
        ClearFilters.click();

        SearchByIdx.click();
        SearchByIdx.fill("11");
        ClearFilters.click();

        SearchByIdx.click();
        SearchByIdx.fill("100");
        ClearFilters.click();
    }

    @Test(priority = 5,description = "Verify that admin can download request product list in excel format")
    public void verifyAdminCanDownloadRequestProductListInExcelFormat() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Req Product")).click();
        Download download = page.waitForDownload(() -> {
            Page page1 = page.waitForPopup(() -> {
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Export to CSV")).click();
            });
        });
    }
}