package AdminPanel;

import base.BaseTest;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class AuditLog extends BaseTest {

    @Test(priority = 1, description = "Verify that the audit log is displayed correctly")
    public void verifyAuditLogDisplay() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Audit")).click();
        page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName("1 View card")).locator("a").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close")).click();
    }

    @Test(priority = 2, description = "Verify that admin can download the audit log")
    public void verifyAuditLogDownload() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Audit")).click();
        Download download1 = page.waitForDownload(() -> {
            Page page2 = page.waitForPopup(() -> {
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Export to CSV")).click();
            });
        });
    }

    @Test(priority = 3, description = "Verify that admin can filter the audit log")
    public void verifyAuditLogFilter() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Audit")).click();
        Locator SearchHere = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search here..."));
        SearchHere.click();
        SearchHere.fill("create");
        Locator ClearFilters = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)"));
        ClearFilters.click();

        SearchHere.click();
        SearchHere.fill("update");
        ClearFilters.click();

        SearchHere.click();
        SearchHere.fill("delete");
        ClearFilters.click();
    }
}
