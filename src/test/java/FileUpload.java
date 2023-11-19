import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;


public class FileUpload {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup(); //Сравнивает текущую версия браузера с драйвером
        //и в случае чего выкачивает нужную
        ChromeOptions options = new ChromeOptions(); //задает хромОпции
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options); //создаю объект драйвера и передаю хромопции
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void fileUpload() {
        driver.get("http://the-internet.herokuapp.com/upload");
        File file = new File("src/test/resources/Jackie.jpg");
        driver.findElement(By.id("file-upload")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();
        String fileName = driver.findElement(By.id("uploaded-files")).getText();
        assertEquals(fileName, "Jackie.jpg", "Wrong filename");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
