import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TyposTest {
    public static final String BASE_URL = "http://the-internet.herokuapp.com/typos";
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
    public void typosSpellingTest() {
        driver.get(BASE_URL);
        for (int i = 1; i < 5; i++) {
            List<WebElement> pTag = driver.findElements(By.tagName("p"));
            String typoText = pTag.get(1).getText();
            driver.navigate().refresh();
            Assert.assertEquals(typoText, "Sometimes you'll see a typo, other times you won't.",
                    "Misspelled word 'won,t'");
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}