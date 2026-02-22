package CustomerEnd;

import base.ValidLogin;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Dashboard extends ValidLogin {

    @Test(priority = 1, description = "Verify that user can see the profile page after login")
    public void verifyUserProfilePage() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("User Profile")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Billing address")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Billing address")).fill("Uttara, Dhaka");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
        page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Verification$"))).click();
        page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Change password$"))).click();
        page.navigate("https://gift.algosoftbd.com/control-panel");
    }

    @Test(priority = 2, description = "Verify that user can do successful top up ")
    public void verifySuccessfulTopUp() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Top Up")).click();
        page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^EPS$"))).first().click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Top up")).click();
        page.locator("div:nth-child(2) > div > ul > li > .paymentSelection").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("PAY BDT")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Success")).click();
        page.navigate("https://gift.algosoftbd.com/control-panel?payment_status=success&payment_text=Payment+successful");
    }

}