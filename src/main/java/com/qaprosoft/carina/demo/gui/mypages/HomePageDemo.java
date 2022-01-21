package com.qaprosoft.carina.demo.gui.mypages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class HomePageDemo extends AbstractPage {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(className = "login_logo")
    private ExtendedWebElement title;

    @FindBy(id = "user-name")
    private ExtendedWebElement userName;

    @FindBy(id = "password")
    private ExtendedWebElement userPassword;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//div[@class='error-message-container error']")
    private ExtendedWebElement errorMassage;


    public HomePageDemo(WebDriver driver) {
        super(driver);
    }

    public boolean isTitlePresent() {
        return title.isElementPresent(5);
    }

    public HomePageDemo typeUserName(String userName) {
        this.userName.type(userName);
        LOGGER.info("Written username: " + userName);
        return this;
    }

    public HomePageDemo typePassword(String password) {
        userPassword.type(password);
        LOGGER.info("Written password: " + password);
        return this;
    }

    public InventoryPage clickLoginButton() {
        loginButton.click();
        LOGGER.info("The Login button is clicked");
        return new InventoryPage(driver);
    }

    public InventoryPage loginToAccount(String username, String password) {
        typeUserName(username);
        typePassword(password);
        clickLoginButton();
        return new InventoryPage(driver);
    }

    public boolean isErrorMassagePresent() {
        return errorMassage.isElementPresent();
    }

    public String checkUsernameTextField() {
        pause(5);
        return userName.getElement().getAttribute("value");
    }
    public String checkPasswordTextField() {
        pause(5);
        return userPassword.getElement().getAttribute("value");
    }

    public boolean checkLocationUsername() {

        LOGGER.info("Password location: " + userPassword.getLocation().toString());
        LOGGER.info("Username location: " + userName.getLocation().toString());

        if(userPassword.getLocation().y > userName.getLocation().y) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkLocationLoginBtn() {

        LOGGER.info("Password location: " + userPassword.getLocation().toString());
        LOGGER.info("Login button location: " + loginButton.getLocation().toString());

        if(userPassword.getLocation().y < loginButton.getLocation().y) {
            return true;
        } else {
            return false;
        }
    }

}
