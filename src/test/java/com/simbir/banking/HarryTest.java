package com.simbir.banking;

import com.simbir.core.BaseSeleniumTest;
import com.simbir.readProperties.ConfigProvider;
import com.simbir.util.CsvWriter;
import com.simbir.util.Fibonacci;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junitpioneer.jupiter.RetryingTest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class HarryTest extends BaseSeleniumTest {

    @RetryingTest(maxAttempts = 10, minSuccess = 2)
    public void checkHarryPotterBanking() throws IOException, ParseException {
        int sum = Fibonacci.get(LocalDate.now().getDayOfMonth() + 1);
        AccountPage accountPage = new MainPage()
                .enter()
                .login(ConfigProvider.USER_NAME)
                .setDeposit(sum)
                .setWithdrawal(sum);
        Assertions.assertEquals(0, accountPage.getBalance());

        TransactionsPage transactionsPage = accountPage.showTransactions();
        Assertions.assertEquals(2, transactionsPage.getTableContent().size());
        Assertions.assertEquals(1, transactionsPage.getNumOfDebits());
        Assertions.assertEquals(1, transactionsPage.getNumOfCredits());
        Assertions.assertEquals(sum, transactionsPage.getDebitSum());
        Assertions.assertEquals(sum, transactionsPage.getCreditSum());

        allureAttachment(transactionsPage.getTableContent());
    }

    private void allureAttachment(List<String[]> tableContent) throws IOException {
        CsvWriter.write(tableContent, "csv/transactions.csv");
        try (InputStream is = Files.newInputStream(Paths.get("csv/transactions.csv"))) {
            Allure.attachment("transactions.csv ", is);
        }
    }
}
