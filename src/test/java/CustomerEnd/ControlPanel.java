package CustomerEnd;

import base.ValidLogin;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.regex.Pattern;

public class ControlPanel extends ValidLogin {

    @Test(priority = 1)
    public void MyOrders() {

        SoftAssert softAssert = new SoftAssert();

        page.locator("div")
                .filter(new Locator.FilterOptions()
                        .setHasText(Pattern.compile("^My Orders$")))
                .click();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        // ✅ Validate using unique content area
        softAssert.assertTrue(
                page.locator("h1:has-text('My Orders')").isVisible(),
                "My Orders content page did not load"
        );

        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void MyCoupons() {

        SoftAssert softAssert = new SoftAssert();

        page.locator("div")
                .filter(new Locator.FilterOptions()
                        .setHasText(Pattern.compile("^My Coupons$")))
                .click();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        // ✅ Validate with unique element
        softAssert.assertTrue(
                page.locator("button:has-text('Create Coupon')").isVisible(),
                "My Coupons page did not load"
        );

        softAssert.assertAll();
    }


    @Test(priority = 3)
    public void MyStars() {

        SoftAssert softAssert = new SoftAssert();

        page.locator("div")
                .filter(new Locator.FilterOptions()
                        .setHasText(Pattern.compile("^My STARs$")))
                .click();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        softAssert.assertTrue(
                page.locator("text=My STARs").isVisible(),
                "My STARs page did not open"
        );

        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Gold")).click();

        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Platinum")).click();

        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Diamond")).click();

        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void MyCredits() {

        SoftAssert softAssert = new SoftAssert();

        page.getByText("My Credits").click();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        softAssert.assertTrue(
                page.locator("text=My Credits").isVisible(),
                "My Credits page did not open"
        );

        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Star")).click();

        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Point")).click();

        softAssert.assertAll();
    }
}
