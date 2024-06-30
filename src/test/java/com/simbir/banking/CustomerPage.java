package com.simbir.banking;

import com.simbir.core.BaseSeleniumPage;
import com.simbir.readProperties.ConfigProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class CustomerPage extends BaseSeleniumPage {

    public CustomerPage() {
        PageFactory.initElements(driver, this);
    }

    public AccountPage login(String userName) {
        driver.findElement(By.xpath("//option[text()=" + "'" + userName + "']")).click();
        driver.findElement(By.className("btn-default")).click();
        return new AccountPage();
    }
}
