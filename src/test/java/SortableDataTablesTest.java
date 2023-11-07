import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SortableDataTablesTest {
    public static final String BASE_URL = "http://the-internet.herokuapp.com/tables";
    public static final String TABLE1 = "//table[@id='table1']/tbody";
    public static final String TABLE2 = "//table[@id='table2']/tbody";
    private WebDriver driver;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public void checkHeaders() {
        driver.get(BASE_URL);
        List<WebElement> header = driver.findElements(By.xpath("//table[@id='table1']/thead/tr/th"));
        String headers = header.get(1).getText();
        Assert.assertEquals(headers, "First Name", "The values don't match");
        headers = header.get(2).getText();
        Assert.assertEquals(headers, "Email", "The values don't match");
    }

    @Test(priority = 1)
    public void checkFields() {
        driver.get(BASE_URL);
        String table1Body = TABLE1;
        String checkField = driver.findElement(By.xpath(table1Body + "/tr[1]/td[1]")).getText();
        Assert.assertEquals(checkField, "Smith", "The values don't match");

        checkField = driver.findElement(By.xpath(table1Body + "/tr[3]/td[3]")).getText();
        Assert.assertEquals(checkField, "jdoe@hotmail.com", "The values don't match");

        checkField = driver.findElement(By.xpath(table1Body + "/tr[3]/td[4]")).getText();
        Assert.assertEquals(checkField, "$100.00", "The values don't match");

        String table2Body = TABLE2;
        checkField = driver.findElement(By.xpath(table2Body + "/tr[1]/td[5]")).getText();
        Assert.assertEquals(checkField, "http://www.jsmith.com", "The values don't match");

        String checkFieldFirstName = driver.findElement(By.xpath(table2Body + "/tr[3]/td[2]")).getText();
        Assert.assertEquals(checkFieldFirstName, "Jason", "The values don't match");

        String checkFieldAction = driver.findElement(By.xpath(table2Body + "/tr[1]/td[6]")).getText();
        Assert.assertEquals(checkFieldAction, "edit delete", "The values don't match");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}