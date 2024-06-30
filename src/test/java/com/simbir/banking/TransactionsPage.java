package com.simbir.banking;

import com.simbir.core.BaseSeleniumPage;
import com.simbir.readProperties.ConfigProvider;
import com.simbir.util.DateTimeParse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TransactionsPage extends BaseSeleniumPage {

    @FindBy(xpath = "//table/tbody")
    private WebElement transactionsTable;

    private final List<String[]> tableContent;

    public TransactionsPage() throws ParseException {
        PageFactory.initElements(driver, this);
        List<WebElement> rows = transactionsTable.findElements(By.tagName("tr"));
        tableContent = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String[] stringRow = new String[cells.size()];
            for (int i = 0; i < cells.size(); i++) {
                if (i == 0)
                    stringRow[i] = DateTimeParse.parse(cells.get(i).getText());
                else
                    stringRow[i] = cells.get(i).getText();
            }
            tableContent.add(stringRow);
        }
    }

    public int getRowsCount() throws InterruptedException {
        return tableContent.size();
    }

    public List<String[]> getTableContent() {
        return tableContent;
    }

}
