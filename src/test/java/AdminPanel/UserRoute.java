package AdminPanel;

import base.BaseTest;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class UserRoute extends BaseTest {

    @Test(priority = 1)
    public void ViewUserDetails() {
        page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(" View Login 1234 mubin6@")).locator("a").first().click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK")).click();
    }

    @Test(priority = 2)
    public void ExportAsCSV() {
        Download download = page.waitForDownload(() -> {
            Page page1 = page.waitForPopup(() -> {
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Export to CSV")).click();
            });
        });
    }

    @Test(priority = 3)
    public void CreateNewUser() {
        Locator Create=page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create"));
        Create.click();

        page.locator("div").filter(new Locator.FilterOptions().setHasText("UserProfile photoNo data")).nth(2).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Upload")).click();
        page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("valorant.png")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Select")).click();

        Locator Name=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Name"));
        Name.click();
        Name.fill("Mr. Fox4");

        Locator Email=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Email"));
        Email.click();
        Email.fill("fox4@gmail.com");

        Locator Phone=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Phone"));
        Phone.click();
        Phone.fill("01234567811");

        Locator Password=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
        Password.click();
        Password.fill("12345678");

        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).press("Enter");
        page.locator("div").filter(new Locator.FilterOptions().setHasText("UserProfile")).nth(2).click();
        page.getByLabel("User").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Create")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close")).click();
    }

    @Test(priority = 4)
    public void SearchUser() {
        Locator Search=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search by name, email or"));
        Search.click();
        Search.fill("Mr.fox");

        page.navigate("https://giftadmin.algosoftbd.com/user?count=20&page=1&search=Mr.Fox");
        Locator Clear= page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)"));
        Clear.click();

        page.locator(".ant-input-affix-wrapper").click();
        Search.fill("fox@gmail.com");
        page.navigate("https://giftadmin.algosoftbd.com/user?count=20&page=1&search=fox@gmail.com");
        Clear.click();
    }

    @Test(priority = 5)
    public void FilterUserLevel() {

        Locator SelectField= page.locator("#rc_select_0");
        SelectField.click();
        page.getByTitle("BRONZE").locator("div").click();
        Locator Clear=page.locator(".ant-select-clear > .anticon > svg");
        Clear.click();

        SelectField.click();
        page.getByTitle("SILVER").locator("div").click();
        Clear.click();

        SelectField.click();
        page.getByTitle("GOLD").locator("div").click();
        Clear.click();

        SelectField.click();
        page.getByText("BOND++").click();
        Clear.click();

        SelectField.click();
        page.getByText("PREMIUM").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)")).click();
    }

    @Test(priority = 6)
    public void FilterUserRole() {
        page.locator("#rc_select_1").click();
        page.getByTitle("Customer").locator("div").click();
        page.locator(".ant-select-clear > .anticon > svg").click();
        page.locator("#rc_select_1").click();
        page.getByText("Admin", new Page.GetByTextOptions().setExact(true)).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)")).click();
    }

    @Test(priority = 7)
    public void FilterUserActiveStatus() {
        page.locator("#rc_select_2").click();
        page.getByText("Active", new Page.GetByTextOptions().setExact(true)).nth(1).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)")).click();
    }

    @Test(priority = 8)
    public void SelectDateFilter(){
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Date from")).click();
        page.getByTitle("-02-01").locator("div").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)")).click();
    }
}
