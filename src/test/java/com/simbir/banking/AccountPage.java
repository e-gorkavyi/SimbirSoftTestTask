package com.simbir.banking;

import com.simbir.core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends BaseSeleniumPage {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
    @FindBy(xpath = "//span[@ng-show='message']")
    private WebElement messageLabel;
    @FindBy(xpath = "//label[text()='Amount to be Withdrawn :']")
    private WebElement withdrawnLabel;
    @FindBy(xpath = "//label[text()='Amount to be Deposited :']")
    private WebElement depositLabel;



    private int balance;

    public AccountPage() {
        PageFactory.initElements(driver, this);
    }

    public int getBalance() {
        return balance;
    }

    public TransactionsPage showTransactions() {
        transactionsButton.click();
        return new TransactionsPage();
    }

    public AccountPage setDeposit(Integer sum) {
        depositButton.click();
        wait.until(ExpectedConditions.visibilityOf(depositLabel));
        depositInput.click();
        depositInput.sendKeys(String.valueOf(sum));
        depositSubmitButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(balanceLabel, String.valueOf(sum)));
        wait.until(ExpectedConditions.textToBePresentInElement(messageLabel, "Deposit Successful"));
        return this;
    }

    public AccountPage setWithdrawal(Integer sum) {
        withdrawalButton.click();
        wait.until(ExpectedConditions.visibilityOf(withdrawnLabel));
        withdrawalInput.click();
        withdrawalInput.sendKeys(String.valueOf(sum));
        withdrawalSubmitButton.click();
        balance = Integer.parseInt(balanceLabel.getText());
        wait.until(ExpectedConditions.textToBePresentInElement(balanceLabel, "0"));
        wait.until(ExpectedConditions.textToBePresentInElement(messageLabel, "Transaction successful"));
        return this;
    }
}
