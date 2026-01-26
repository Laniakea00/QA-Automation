package org.qa.alanb.selenium.assignment4.tests;

import org.qa.alanb.BaseTest;
import org.qa.alanb.selenium.assignment4.pages.ConfirmationPage;
import org.qa.alanb.selenium.assignment4.pages.FlightsPage;
import org.qa.alanb.selenium.assignment4.pages.HomePage;
import org.qa.alanb.selenium.assignment4.pages.PurchasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookFlightTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BookFlightTest.class);


    @Test
    void bookFlightPositiveTest() throws InterruptedException {
        logTest("bookFlightPositiveTest", () -> {
            HomePage homePage = new HomePage(driver);
            FlightsPage flightsPage = new FlightsPage(driver);
            PurchasePage purchasePage = new PurchasePage(driver);
            ConfirmationPage confirmationPage = new ConfirmationPage(driver);

            homePage.open();
            homePage.searchFlight("Boston", "London");

            flightsPage.chooseFirstFlight();

            purchasePage.fillForm(
                    "Alan",
                    "Berikzhan st.",
                    "Astana",
                    "1111111111111111"
            );
            purchasePage.submit();

            assertEquals(
                    "Thank you for your purchase today!",
                    confirmationPage.getTitleText()
            );


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void bookFlightNegativeTest_wrongCardNum() throws InterruptedException {
        logTest("bookFlightNegativeTest_emptyName", () -> {
            HomePage homePage = new HomePage(driver);
            FlightsPage flightsPage = new FlightsPage(driver);
            PurchasePage purchasePage = new PurchasePage(driver);
            ConfirmationPage confirmationPage = new ConfirmationPage(driver);

            homePage.open();
            homePage.searchFlight("Boston", "London");

            flightsPage.chooseFirstFlight();

            String cardNumber = "4444444444444444";

            purchasePage.fillForm(
                    "",
                    "Petrov st.",
                    "Astana",
                    cardNumber
            );
            purchasePage.submit();

            assertEquals(
                    cardNumber.substring(cardNumber.length() - 4),
                    confirmationPage.getCardNumber().substring(cardNumber.length() - 4)
            );

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }

    protected void logTest(String testName, Runnable testLogic) throws InterruptedException{
        try {
            testLogic.run();
        } catch (Throwable e) {
            LOGGER.error("Test {} failed with message: {}", testName, e.getMessage());
            throw e;
        }
    }
}
