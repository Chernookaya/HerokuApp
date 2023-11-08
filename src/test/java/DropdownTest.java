import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DropdownTest {
    public static final String BASE_URL = "http://the-internet.herokuapp.com/dropdown";
    WebDriver driver;

    @BeforeMethod
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void dropdown() {
        driver.get(BASE_URL);
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();
        Assert.assertEquals(options.get(0).getText(), "Please select an option");
        Assert.assertEquals(options.get(1).getText(), "Option 1");
        Assert.assertEquals(options.get(2).getText(), "Option 2");
        select.selectByVisibleText("Option 1");
        select.getFirstSelectedOption().isSelected();
        select.selectByVisibleText("Option 2");
        select.getFirstSelectedOption().isSelected();
    }

    @Test(enabled = false)
    public void dropdownTest() {
        driver.get(BASE_URL);
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Option 1");
        String selectedOption1 = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedOption1, "Option 1");
        select.selectByVisibleText("Option 2");
        String selectedOption2 = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedOption2, "Option 2");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}