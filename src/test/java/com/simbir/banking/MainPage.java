package com.simbir.banking;

import com.simbir.core.BaseSeleniumPage;
import com.simbir.readProperties.ConfigProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class MainPage extends BaseSeleniumPage {
    @FindBy(xpath = "//button[text()='Customer Login']")
    private WebElement customerLoginButton;

    public MainPage() {
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
    }

    public CustomerPage enter() {
        customerLoginButton.click();
        return new CustomerPage();
    }
}
