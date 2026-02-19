package AdminPanel;

import base.BaseTest;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class ContactMessage extends BaseTest {

    @Test(priority = 1, description = "Verify that admin can download contact messages list")
    public void DownloadContactMessages() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact Message")).click();
        Download download = page.waitForDownload(() -> {
            Page page1 = page.waitForPopup(() -> {
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Export to CSV")).click();
            });
        });
    }

    @Test(priority = 2, description = "Verify that admin can view contact message details")
    public void ViewContactMessageDetails() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact Message")).click();
        page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName("1 View GCZC14")).locator("a").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK")).click();
    }

    @Test(priority = 3, description = "Verify that admin can search for contact messages")
    public void SearchContactMessages() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact Message")).click();

        Locator SearchBox=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search with idx"));
        SearchBox.click();
        SearchBox.fill("10");

        Locator ClearFilters= page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)"));
        ClearFilters.click();

        SearchBox.click();
        SearchBox.fill("15");
        ClearFilters.click();

        SearchBox.click();
        SearchBox.fill("50");
        ClearFilters.click();
    }

    @Test(priority = 4,description = "Verify that admin can changes the status of contact message")
    public void ChangeContactMessageStatus() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact Message")).click();

        Locator SelectContactMessage= page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName("1 View GCZC12")).getByRole(AriaRole.BUTTON);
        SelectContactMessage.click();
        page.getByRole(AriaRole.TOOLTIP).getByText("Ongoing").click();
        Locator YesButton= page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes, do it"));
        YesButton.click();

        SelectContactMessage.click();
        page.getByRole(AriaRole.TOOLTIP).getByText("Pending").click();
        YesButton.click();

        SelectContactMessage.click();
        page.getByRole(AriaRole.TOOLTIP).getByText("Solved").click();
        YesButton.click();

        SelectContactMessage.click();
        page.getByRole(AriaRole.TOOLTIP).getByText("Closed").click();
        YesButton.click();

        SelectContactMessage.click();
        page.getByText("Delete").click();
        YesButton.click();
    }

    @Test(priority = 5,description = "Verify that admin can filter contact messages by status")
    public void FilterContactMessagesByStatus() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact Message")).click();

        Locator SelectStatusField=page.locator("#rc_select_6");
        Locator ClearFiltersButton=page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)"));

        SelectStatusField.click();
        page.getByTitle("Pending").click();
        ClearFiltersButton.click();

        SelectStatusField.click();
        page.getByTitle("Ongoing").locator("div").click();
        ClearFiltersButton.click();

        SelectStatusField.click();
        page.getByTitle("Solved").locator("div").click();
        ClearFiltersButton.click();

        SelectStatusField.click();
        page.getByTitle("Closed").locator("div").click();
        ClearFiltersButton.click();
    }
}
