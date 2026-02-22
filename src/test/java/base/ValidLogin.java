package base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ValidLogin {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeMethod()
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        page = browser.newPage();
        page.navigate("https://gift.algosoftbd.com/");
        page.locator("text=Sign in").click();
        page.locator("input[id='mantine-re']").fill(" 01121212121");
        page.locator("input[id='mantine-rf']").fill("12345678");
        page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).click();
        page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).click();
        page.locator("button:has-text('Log in')").click();
        assertThat(page).hasTitle("Gift Cards, Games Top Up & More | Instant Delivery - GCZBD");
    }
    @AfterMethod()
    public void teardown(){
        browser.close();
        playwright.close();
    }
}
