package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product", testBaseUrl, serverPort);
    }

    @Test
    void testProductCreation_chrome(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/list");

        WebElement createButtonLink = driver.findElement(By.cssSelector("[aria-label='Create Product']"));
        createButtonLink.click();

        assertEquals(baseUrl + "/create", driver.getCurrentUrl());

        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("[aria-label='Submit']"));

        productNameInput.clear();
        productNameInput.sendKeys("Kagurabachi Vol. 1");
        productQuantityInput.clear();
        productQuantityInput.sendKeys("1000");
        submitButton.click();

        assertEquals(baseUrl + "/list", driver.getCurrentUrl());

        WebElement productListBody = driver.findElement(By.id("list-product")).findElement(By.tagName("tbody"));
        List<WebElement> rows = productListBody.findElements(By.tagName("tr"));
        assertEquals(1, rows.size());

        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            int index = 0;
            for (WebElement col : cols) {
                if (index == 0) {
                    assertEquals("Kagurabachi Vol. 1", col.getText());
                }
                if (index == 1) {
                    assertEquals("1000", col.getText());
                }
                if (index++ > 2) {
                    break;
                }
            }
        }
    }
}
