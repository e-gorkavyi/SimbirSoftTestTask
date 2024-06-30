package com.simbir.banking;

import com.simbir.core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;

public class AccountPage extends BaseSeleniumPage {
    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    private WebElement transactionsButton;

    @FindBy(xpath = "//button[@ng-class='btnClass2']")
    private WebElement depositButton;

    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    private WebElement withdrawalButton;

    @FindBy(xpath = "//form[@ng-submit='deposit()']//input")
    private WebElement depositInput;

    @FindBy(xpath = "//form[@ng-submit='withdrawl()']//input")
    private WebElement withdrawalInput;

    @FindBy(xpath = "//form[@ng-submit='deposit()']//button")
    private WebElement depositSubmitButton;

    @FindBy(xpath = "//form[@ng-submit='withdrawl()']//button")
    private WebElement withdrawalSubmitButton;

    @FindBy(xpath = "//div[text()[contains(.,'Balance')]]/strong[2]")
    private WebElement balanceLabel;

    private int balance;

    public int getBalance() {
        return balance;
    }

    public AccountPage() {
        PageFactory.initElements(driver, this);
    }

    public TransactionsPage showTransactions() throws InterruptedException, ParseException {
        Thread.sleep(1000);
        transactionsButton.click();
        return new TransactionsPage();
    }

    public AccountPage setDeposit(Integer sum) {
        depositButton.click();
        depositInput.click();
        depositInput.sendKeys(String.valueOf(sum));
        depositSubmitButton.click();
        return this;
    }

    public AccountPage setWithdrawal(Integer sum) {
        withdrawalButton.click();
        withdrawalInput.click();
        withdrawalInput.sendKeys(String.valueOf(sum));
        withdrawalSubmitButton.click();
        balance = Integer.parseInt(balanceLabel.getText());
        return this;
    }
}
