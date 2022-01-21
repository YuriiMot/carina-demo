package com.qaprosoft.carina.demo.myweb;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.gui.mypages.HomePageDemo;
import com.qaprosoft.carina.demo.gui.mypages.InventoryPage;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class MyWebTest implements IAbstractTest {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String USER_NAME = "standard_user";
    private static final String USER_PASSWORD = "secret_sauce";
    private static final String FAIL_USER_NAME = "Username";
    private static final String FAIL_USER_PASSWORD = "1234567";

    @Test
    public void testHomeTitle() {
        HomePageDemo homePageDemo = new HomePageDemo(getDriver());
        homePageDemo.open();
        Assert.assertTrue(homePageDemo.isTitlePresent(), "Home page title isn`t present");
    }

    @Test
    public void testSuccessLogin() {
        HomePageDemo homePageDemo = new HomePageDemo(getDriver());
        homePageDemo.open();
        Assert.assertTrue(homePageDemo.isPageOpened(), "Home page is not opened!");
        Assert.assertNotEquals(homePageDemo.checkUsernameTextField(), USER_NAME, "User name is equals");
        Assert.assertNotEquals(homePageDemo.checkPasswordTextField(), USER_PASSWORD,"Password is equals");
        InventoryPage inventoryPage = homePageDemo.clickLoginButton();
        // Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page is not opened!");
        Assert.assertTrue(inventoryPage.isShoppingCartPresent(), "Shopping cart isn`t present");
        Assert.assertTrue(inventoryPage.isTitleProductsPresent(), "Title Products isn`t present");
    }

    @Test
    public void testFailLogin() {
        HomePageDemo homePageDemo = new HomePageDemo(getDriver());
        homePageDemo.open();
        Assert.assertNotEquals(homePageDemo.checkUsernameTextField(), FAIL_USER_NAME, "User name is equals");
        Assert.assertNotEquals(homePageDemo.checkPasswordTextField(), FAIL_USER_PASSWORD,"Password is equals");
        homePageDemo.clickLoginButton();
        Assert.assertTrue(homePageDemo.isErrorMassagePresent(), "Error massage isn`t present");
    }

    @Test   // 1
    public void testRightPosition() {
        HomePageDemo homePageDemo = new HomePageDemo(getDriver());
        homePageDemo.open();
        Assert.assertTrue(homePageDemo.checkLocationUsername(), "Username field is not above password field");
        Assert.assertTrue(homePageDemo.checkLocationLoginBtn(), "Login btn is not below password field");
    }

    @Test   //2
    public void testProductItemCard() {
        HomePageDemo homePageDemo = new HomePageDemo(getDriver());
        homePageDemo.open();
        InventoryPage inventoryPage = homePageDemo.loginToAccount(USER_NAME, USER_PASSWORD);
       // Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page is not opened!");
        Assert.assertTrue(inventoryPage.isInventoryItemImgPresent(), "Inventory item img is not present");
        Assert.assertTrue(inventoryPage.isInventoryItemNamePresent(), "Inventory item name is not present");
        Assert.assertTrue(inventoryPage.isInventoryItemDescPresent(), "Inventory item description is not present");
        Assert.assertTrue(inventoryPage.isInventoryItemPricePresent(), "Inventory item price is not present");
        Assert.assertTrue(inventoryPage.isAddToCartButtonPresent(), "Add to cart button is not present");
    }

    @Test   //3
    public void testProductSort() {
        HomePageDemo homePageDemo = new HomePageDemo(getDriver());
        homePageDemo.open();
        InventoryPage inventoryPage = homePageDemo.loginToAccount(USER_NAME, USER_PASSWORD);
        // Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page is not opened!");
        Assert.assertTrue(inventoryPage.isSort_AZ_Present(), "Sort A-Z isn`t present");
        Assert.assertTrue(inventoryPage.isSort_ZA_Present(), "Sort Z-A isn`t present");
        Assert.assertTrue(inventoryPage.isSortHighToLowPresent(), "Sort High to Low isn`t present");
        Assert.assertTrue(inventoryPage.isSortLowToHighPresent(), "Sort Low to High isn`t present");
    }

    @Test   //4
    public void testDropDownFilterMenu() {
        HomePageDemo homePageDemo = new HomePageDemo(getDriver());
        homePageDemo.open();
        InventoryPage inventoryPage = homePageDemo.loginToAccount(USER_NAME, USER_PASSWORD);
        // Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page is not opened!");
        Assert.assertTrue(inventoryPage.clickSort_ZA(), "(Z-A) filter isn`t present");
        Assert.assertTrue(inventoryPage.clickSort_AZ(), "(A-Z) filter isn`t present");
    }

    @MethodOwner(owner = "YuriiMot")
    @Test(description = "Check sorting products by filter") // 5*
    public void testSortByAlphabetical() {
        HomePageDemo homePageDemo = new HomePageDemo(getDriver());
        homePageDemo.open();
        InventoryPage inventoryPage = homePageDemo.loginToAccount(USER_NAME, USER_PASSWORD);
        // Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page is not opened!");
        Assert.assertTrue(inventoryPage.checkSortProducts_AZ(), "The array is not sorted correctly.");
        Assert.assertTrue(inventoryPage.clickSort_ZA(), "(Z-A) filter isn`t present");
        Assert.assertTrue(inventoryPage.checkSortProducts_ZA(), "The array is not sorted correctly.");
    }
}
