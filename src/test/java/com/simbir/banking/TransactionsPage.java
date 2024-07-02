package com.simbir.banking;

import com.simbir.core.BaseSeleniumPage;
import com.simbir.readProperties.ConfigProvider;
import com.simbir.util.DateTimeConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TransactionsPage extends BaseSeleniumPage {

    private List<String[]> tableContent;

    @FindAll({@FindBy(xpath = "//table/tbody/tr")})
    private List<WebElement> tableRows;

    public TransactionsPage() {
        driver.get(ConfigProvider.URL + "/#/listTx");
        PageFactory.initElements(driver, this);
    }

    public List<String[]> getTableContent() throws ParseException {

        if (tableContent != null)
            return tableContent;

        driver.navigate().refresh(); // - костыль, без которого % положительных тестов очень низкий

        tableContent = new ArrayList<>();
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String[] stringRow = new String[cells.size()];
            for (int i = 0; i < cells.size(); i++) {
                if (i == 0)
                    stringRow[i] = DateTimeConverter.parse(cells.get(i).getText());
                else
                    stringRow[i] = cells.get(i).getText();
            }
            tableContent.add(stringRow);
        }
        return tableContent;
    }

    public int getNumOfDebits() throws ParseException {
        int debs = 0;
        for (String[] row : getTableContent())
            if (row[2].equals("Debit"))
                debs++;
        return debs;
    }

    public int getNumOfCredits() throws ParseException {
        int creds = 0;
        for (String[] row : getTableContent())
            if (row[2].equals("Credit"))
                creds++;
        return creds;
    }

    public int getDebitSum() throws ParseException {
        int debSum = 0;
        for (String[] row : getTableContent())
            if (row[2].equals("Debit"))
                debSum = Integer.parseInt(row[1]);
        return debSum;

    }

    public int getCreditSum() throws ParseException {
        int credSum = 0;
        for (String[] row : getTableContent())
            if (row[2].equals("Credit"))
                credSum = Integer.parseInt(row[1]);
        return credSum;

    }
}
