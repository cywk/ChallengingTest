package com.herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SimpleTest {

    private WebDriver driver;
    String BaseUrl = ("https://the-internet.herokuapp.com/challenging_dom");


    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\drivers\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.navigate().to(BaseUrl);
        WebElement header = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(driver -> driver.findElement(By.cssSelector("div[class='example'] h3")));
         //Test to validate Header text & Page tittle
        Assert.assertEquals(header.getText(), "Challenging DOM");
        Assert.assertEquals(driver.getTitle(), "The Internet");
        System.out.println(driver.findElement(By.cssSelector("div[class='example'] h3")).getText());
        System.out.println(driver.getTitle());

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }

    @Test
    // 'This test validate Blue button is displayed & button text is not null'
    public void testBlueButton() {
        WebElement buttons = driver.findElement(By.className("button"));
        Assert.assertTrue(buttons.isDisplayed());
        Assert.assertNotNull(buttons.getText());
        System.out.println(buttons.getText());
    }

    @Test
    // 'This test will validate Red button is displayed & button text is not null'
    public void testRedAlertButton() {
        WebElement buttonAlert = driver.findElement(By.xpath("//*[contains(@class,'alert')]"));
        Assert.assertTrue(buttonAlert.isDisplayed());
        Assert.assertNotNull(buttonAlert.getText());
        System.out.println(buttonAlert.getText());
    }

    @Test
    // 'This test will validate Green button is displayed & button text is not null'
    public void testGreenSuccessButton() {
        WebElement buttonSuccess = driver.findElement(By.xpath("//*[contains(@class,'success')]"));
        Assert.assertTrue(buttonSuccess.isDisplayed());
        Assert.assertNotNull(buttonSuccess.getText());
        System.out.println(buttonSuccess.getText());
    }

    @Test
    // 'This test will validate Table row are equal to 10'
    public void testTableRowLength() {
        List<WebElement> tableRows = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(tableRows.size(), 10);
        System.out.println(tableRows.size());
    }

    @Test
    // 'This test will validate Table data are equal to 70'
    public void testTableDataLength() {
        List<WebElement> tableData = driver.findElements(By.tagName("td"));
        Assert.assertEquals(tableData.size(), 70);
        System.out.println(tableData.size());
    }

    @Test
    // 'This validate each Header on the table has text'
    public void testHeaders() {
        List<WebElement> headers = driver.findElements(By.tagName("th"));
        Assert.assertEquals(headers.size(), 7);
        Assert.assertEquals(headers.get(0).getText(), "Lorem");
        Assert.assertEquals(headers.get(1).getText(), "Ipsum");
        Assert.assertEquals(headers.get(2).getText(), "Dolor");
        Assert.assertEquals(headers.get(3).getText(), "Sit");
        Assert.assertEquals(headers.get(4).getText(), "Amet");
        Assert.assertEquals(headers.get(5).getText(), "Diceret");
        Assert.assertEquals(headers.get(6).getText(), "Action");
        System.out.println(headers.size());
        System.out.println(headers.get(1).getText());

    }

    @Test
    // 'This test validate each row and each column has text'
    public void testTableData() {
        List<WebElement> tableRows = driver.findElements(By.xpath("//tbody/tr"));

        for (int i = 0; i < tableRows.size(); i++) {
            List<WebElement> tableData = driver.findElements(By.xpath("//table/tbody/tr[" + i + "]/td"));
            for (int j = 1; j < tableData.size(); j++) {
                Assert.assertNotNull(driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[" + j + "]")).getText());
            }

        }
    }

    @Test
    // 'This test validate each row has edit & delete options and as text'
    public void testEditDeleteAction() {
        List<WebElement> tableRows = driver.findElements(By.xpath("//tbody/tr"));

        for (int i = 1; i < tableRows.size(); i++) {
            List<WebElement> actionHrefs = driver.findElements(By.xpath("//table/tbody/tr["+i+"]/td[7]/a"));
            Assert.assertEquals(actionHrefs.get(0).getText(),"edit");
            Assert.assertEquals(actionHrefs.get(1).getText(),"delete");
        }
    }

    @Test
    // 'This test validate there is Canvas displayed'
    public void testCanvas() {
        WebElement canvas = driver.findElement(By.id("canvas"));
        Assert.assertTrue((canvas.isDisplayed()));
        System.out.println(driver.findElement(By.id("canvas")).isDisplayed());
    }

   /* Tests
    1. validate Header text & Page tittle
    2. validate Blue button is displayed & button text is not null
    3. validate Red button is displayed & button text is not null
    4. validate Green button is displayed & button text is not null
    5. validate Table row are equal to 10
    6. validate Table data are equal to 70
    7. validate each Header on the table has text
    8. This test validate each row and each column has text
    9. validate each row has edit & delete options and as text
    10. validate there is Canvas displayed*/

}
