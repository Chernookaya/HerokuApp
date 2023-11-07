import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NotificationMessagesTest {
    public static final String BASE_URL = "http://the-internet.herokuapp.com/notification_message_rendered";
    String expectedResult = "Action successful";
    String actualResult;
    private WebDriver driver;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(enabled = false)
    public void notificationMessage() {
        driver.get(BASE_URL);
        driver.findElement(By.linkText("Click here")).click();
        actualResult = driver.findElement(By.xpath("//div[@class = 'flash notice']")).getAttribute("textContent");
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test()
    public void notificationMessage2() {
        driver.get(BASE_URL);
        boolean actualResult = true;
        String firstNotificationMessage = "Action unsuccesful, please try again\n" + '×';
        String secondNotificationMessage = "Action successful\n" + '×';
        driver.findElement(By.linkText("Click here")).click();
        String messageText = driver.findElement(By.id("flash")).getText();
        actualResult = (messageText.equals(firstNotificationMessage) || messageText.equals(secondNotificationMessage));
        Assert.assertTrue(actualResult, "Wrong another message");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}