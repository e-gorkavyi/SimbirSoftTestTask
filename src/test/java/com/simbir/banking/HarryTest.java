package com.simbir.banking;

import com.simbir.core.BaseSeleniumTest;
import com.simbir.readProperties.ConfigProvider;
import com.simbir.util.CsvWrite;
import com.simbir.util.Fibonacci;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HarryTest extends BaseSeleniumTest {

    @Test
    public void checkHarryPotterBanking() throws IOException, InterruptedException, ParseException {
        int sum = Fibonacci.fib(LocalDate.now().getDayOfMonth() + 1);
        AccountPage accountPage = new MainPage()
                .enter()
                .login(ConfigProvider.USER_NAME)
                .setDeposit(sum)
                .setWithdrawal(sum);
        Assertions.assertEquals(0, accountPage.getBalance());

        TransactionsPage transactionsPage = accountPage.showTransactions();
        Assertions.assertEquals(2, transactionsPage.getRowsCount());
        int debs = 0;
        int creds = 0;
        int debSum = 0;
        int credSum = 0;
        for (String[] row : transactionsPage.getTableContent()) {
            if (row[2].equals("Credit")) {
                creds++;
                credSum = Integer.parseInt(row[1]);
            }
            if (row[2].equals("Debit")) {
                debs++;
                debSum = Integer.parseInt(row[1]);
            }
        }

        Assertions.assertEquals(1, debs);
        Assertions.assertEquals(1, creds);
        Assertions.assertEquals(sum, debSum);
        Assertions.assertEquals(sum, credSum);

        CsvWrite.write(transactionsPage.getTableContent(), "csv/transactions.csv");
        try (InputStream is = Files.newInputStream(Paths.get("csv/transactions.csv"))) {
            Allure.attachment("transactions.csv ", is);
        }
    }
}
