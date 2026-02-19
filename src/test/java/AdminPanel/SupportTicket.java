package AdminPanel;

import base.BaseTest;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.Test;

public class SupportTicket extends BaseTest {
    // No test cases for support ticket as of now
    @Test(priority = 1, description = "Verify that admin can download suport tickets list")
    public void DownloadSupportTickets() {

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Support Ticket")).click();
        Download download = page.waitForDownload(() -> {
            Page page1 = page.waitForPopup(() -> {
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Export to CSV")).click();
            });
        });
    }

    @Test(priority = 2, description = "Verify that admin can view support ticket details")
    public void ViewSupportTicketDetails() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Support Ticket")).click();
        page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(" View GCZST23 ")).locator("a").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close")).click();
    }

    @Test(priority = 3, description = "Verify that admin can do visual filtering for support tickets")
    public void VisualFilteringSupportTickets() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Support Ticket")).click();

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("PENDING")).click();
        Locator ClearFilters= page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)"));
        ClearFilters.click();

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("SOLVED")).click();
        ClearFilters.click();

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CLOSED")).click();
        ClearFilters.click();

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("ONGOING")).click();
        ClearFilters.click();
    }

    @Test(priority = 4, description = "Verify that admin can search for support tickets")
    public void SearchSupportTickets() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Support Ticket")).click();

        Locator SearchField=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search here..."));
        SearchField.click();
        SearchField.fill("GCZST10");
        Locator ClearsFilter=page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)"));
        ClearsFilter.click();

        SearchField.click();
        SearchField.fill("GCZST23");
        ClearsFilter.click();
    }

}
