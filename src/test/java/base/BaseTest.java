package base;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

    public class BaseTest {

        protected Playwright playwright;
        protected Browser browser;
        protected Page page;

        @BeforeMethod
        public void setup() {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
            );
            page = browser.newPage();

            // Navigate to the admin login page

            page.navigate("https://giftadmin.algosoftbd.com/");
            Locator PhoneNumber= page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Phone"));
            PhoneNumber.click();

            PhoneNumber.fill("01831925544");
            Locator Email=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Email"));
            Email.click();
            Email.fill("mubin986@gmail.com");

            Locator Password=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Password"));
            Password.click();
            Password.fill("hello123");

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
            page.getByText("Use backup code").click();

            Locator BackUpCode=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Backup Code"));
            BackUpCode.click();
            BackUpCode.fill("123456");

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Use backup code")).click();
        }

        @AfterMethod
        public void tearDown() {
            browser.close();
            playwright.close();
        }
    }

