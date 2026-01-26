package org.qa.alanb.apache.assignment6.test;

import org.qa.alanb.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.qa.alanb.apache.assignment6.utils.ExcelUtils;
import org.qa.alanb.selenium.assignment5.pages.*;

public class CheckoutExcelTest extends BaseTest {

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return ExcelUtils.readSheet("testdata.xlsx", "checkout");
    }

    @Test(dataProvider = "checkoutData")
    public void checkout_success_from_excel(
            String username,
            String password,
            String productName,
            String firstName,
            String lastName,
            String postalCode
    ) {
        LoginPage login = new LoginPage(driver);
        ProductsPage products = new ProductsPage(driver);
        CartPage cart = new CartPage(driver);
        CheckoutInfoPage info = new CheckoutInfoPage(driver);
        CheckoutOverviewPage overview = new CheckoutOverviewPage(driver);
        CheckoutCompletePage complete = new CheckoutCompletePage(driver);

        login.openLogin();
        login.login(username, password);

        Assert.assertEquals(products.getTitle(), "Products");

        products.addToCartByName(productName);
        products.openCart();

        cart.goToCheckout();

        info.fillCustomerInfo(firstName, lastName, postalCode);
        info.continueCheckout();

        overview.finish();

        Assert.assertTrue(complete.getCompleteHeader().contains("Thank you for your order!"));
    }
}

