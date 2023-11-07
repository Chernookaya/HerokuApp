import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import static org.openqa.selenium.Keys.ARROW_DOWN;
import static org.openqa.selenium.Keys.ARROW_UP;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class InputsTest {
    public static final String BASE_URL = "http://the-internet.herokuapp.com/inputs";
    private WebDriver driver;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void inputNumber() {
        driver.get(BASE_URL);
        WebElement element = driver.findElement(By.cssSelector("input[type=number]"));
        element.click();
        element.sendKeys(Keys.chord(ARROW_UP, ARROW_UP, ARROW_UP, ARROW_UP, ARROW_UP));
        int checkNumber = Integer.valueOf(element.getAttribute("value"));
        Assert.assertEquals(checkNumber, 5, "Wrong digital entered");
        element.clear();
        element.sendKeys(Keys.chord(ARROW_DOWN, ARROW_DOWN, ARROW_DOWN, ARROW_DOWN, ARROW_DOWN));
        checkNumber = Integer.valueOf(element.getAttribute("value"));
        Assert.assertEquals(checkNumber, -5, "Wrong digital entered");
        element.clear();
        element.sendKeys(Keys.chord(ARROW_UP, ARROW_DOWN));
        checkNumber = Integer.valueOf(element.getAttribute("value"));
        Assert.assertEquals(checkNumber, 0, "Wrong digital entered");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}