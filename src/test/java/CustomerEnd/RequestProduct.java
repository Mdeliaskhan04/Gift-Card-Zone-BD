package CustomerEnd;

import base.ValidLogin;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class RequestProduct extends ValidLogin {
    @Test(priority = 1)
    public void NewProductRequest(){
    page.click("text=Request Product");
    page.getByPlaceholder("Enter product name").fill("PlayStation 5");
    page.getByPlaceholder("Write your message").fill("I would like to request the PlayStation 5 to be added to your product list.");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/new request.png"))
                .setFullPage(true));

        page.click("button:has-text('Send Message')");
    }

    @Test(priority = 2)
    public void ReStockRequest(){
        page.click("text=Request Product");
        page.getByText("Re-stock").click();
        page.getByPlaceholder("Enter product name").fill("PlayStation 5");
        page.getByPlaceholder("Write your message").fill("I would like to request the PlayStation 5 to be restocked.");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/re-stock.png"))
                .setFullPage(true));

        page.click("button:has-text('Send Message')");
    }
}
