package AdminPanel;

import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdminLogin extends BaseTest {

    // ==============================
    // TC01 – Successful Login
    // ==============================
    @Test(priority = 1)
    public void verifySuccessfulLogin() {

        SoftAssert softAssert = new SoftAssert();

        // -------- Step 1: Enter Phone --------
        page.locator("input[id='phone']").fill("01831925544");
        softAssert.assertEquals(
                page.locator("input[id='phone']").inputValue(),
                "01831925544",
                "Phone number not entered correctly"
        );

        // -------- Step 2: Enter Email --------
        page.locator("input[id='email']").fill("mubin986@gmail.com");
        softAssert.assertEquals(
                page.locator("input[id='email']").inputValue(),
                "mubin986@gmail.com",
                "Email not entered correctly"
        );

        // -------- Step 3: Enter Password --------
        page.locator("input[id='password']").fill("hello123");
        softAssert.assertEquals(
                page.locator("input[id='password']").inputValue(),
                "hello123",
                "Password not entered correctly"
        );

        // -------- Step 4: Click Sign In --------
        page.locator("button:has-text('Sign In')").click();
        page.waitForSelector("text=Use backup code");

        softAssert.assertTrue(
                page.locator("text=Use backup code").isVisible(),
                "Use backup code link not visible"
        );

        // -------- Step 5: Click Use backup code --------
        page.locator("text=Use backup code").click();
        page.waitForSelector("input[id='code']");

        softAssert.assertTrue(
                page.locator("input[id='code']").isVisible(),
                "Backup code input field not visible"
        );

        // -------- Step 6: Enter Valid OTP --------
        page.locator("input[id='code']").fill("123456");
        softAssert.assertEquals(
                page.locator("input[id='code']").inputValue(),
                "123456",
                "Backup OTP not entered correctly"
        );

        // -------- Step 7: Click Use backup code Button --------
        page.locator("button:has-text('Use backup code')").click();
        // -------- Wait for redirect --------
        page.waitForURL("https://giftadmin.algosoftbd.com/user?count=20&page=1");
    }


    // ==============================
    // TC02 – Unsuccessful Login (Invalid OTP)
    // ==============================
    @Test(priority = 2)
    public void verifyUnsuccessfulLogin() {

        SoftAssert softAssert = new SoftAssert();

        page.locator("input[id='phone']").fill("01831925544");
        page.locator("input[id='email']").fill("mubin986@gmail.com");
        page.locator("input[id='password']").fill("hello123");
        page.locator("button:has-text('Sign In')").click();

        page.waitForSelector("text=Use backup code");
        page.locator("text=Use backup code").click();

        page.waitForSelector("input[id='code']");
        page.locator("input[id='code']").fill("12345"); // Invalid OTP (5 digit)

        page.locator("button:has-text('Use backup code')").click();

        // Validate error
        page.waitForSelector("text=Invalid backup code");

        softAssert.assertTrue(
                page.locator("text=Invalid backup code").isVisible(),
                "Invalid backup code message not displayed"
        );

        // Ensure still on OTP page
        softAssert.assertFalse(
                page.url().contains("/user"),
                "Invalid OTP still redirected to user page"
        );

        softAssert.assertAll();
    }

}
