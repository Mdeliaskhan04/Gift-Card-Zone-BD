package AdminPanel;

import base.BaseTest;
import base.ValidLogin;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.internal.shadowed.jackson.databind.ser.Serializers;
import org.testng.annotations.Test;

public class UserLevelRoute extends BaseTest {

    @Test(priority = 1,description = "Verify that admin can create new user level")
    public void createUserLevel(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Level")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Level Name")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Level Name")).fill("GCZBD Pro");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Required Points (1 taka =")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Required Points (1 taka =")).fill("1000000");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Duration (in days)")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Duration (in days)")).fill("90");
        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("* Default Level")).click();
        page.getByRole(AriaRole.PARAGRAPH).filter(new Locator.FilterOptions().setHasText("No")).click();
        page.getByLabel("Card").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Create")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close")).click();

    }

    @Test(priority = 2,description = "Verify that admin can search user level")
    public void searchUserLevel(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Level")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search by title")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search by title")).fill("SILVER");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search by title")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search by title")).fill("Gold");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search by title")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search by title")).fill("MT");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear filters (1)")).click();
    }

    @Test(priority = 3,description = "Verify that admin can download user level list")
    public void downloadUserLevelList(){
        Download download = page.waitForDownload(() -> {
            Page page1 = page.waitForPopup(() -> {
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Export to CSV")).click();
            });
        });
        }

        @Test(priority = 4,description = "Verify that admin can delete user level")
        public void deleteUserLevel(){
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Level")).click();
            page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName("6 View GCZBD PRO 1000000 90")).getByRole(AriaRole.BUTTON).click();
            page.getByText("Delete").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes, do it")).click();
        }

        @Test(priority = 5,description = "Verify that admin can navigate from User level to User list in this level")
    public void Navigation(){
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Level")).click();
            page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName("3 View GOLD 30000 90 days No")).getByRole(AriaRole.BUTTON).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Users in this Level")).click();
            page.navigate("https://giftadmin.algosoftbd.com/level?count=20&page=1");
        }

       @Test(priority = 6,description = "Verify that admin can view user level details")
    public void viewUserLevelDetails() {
           page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Level")).click();
           page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName("1 View BRONZE 0 0 days Yes 22")).locator("a").click();
           page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK")).click();
       }
}
