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

public class CheckboxesTest {
    public static final String BASE_URL = "http://the-internet.herokuapp.com/checkboxes";
    WebDriver driver;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public void checkBoxes() {
        driver.get(BASE_URL);
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));
        boolean isFirstCheckboxChecked = checkboxes.get(0).isSelected();
        Assert.assertFalse(isFirstCheckboxChecked, "First Checkbox is checked!");
        checkboxes.get(0).click();
        isFirstCheckboxChecked = checkboxes.get(0).isSelected();
        Assert.assertTrue(isFirstCheckboxChecked, "First Checkbox is unchecked!");

        boolean isSecondCheckboxChecked = checkboxes.get(1).isSelected();
        Assert.assertTrue(isSecondCheckboxChecked, "Second Checkbox is unchecked");
        checkboxes.get(1).click();
        isSecondCheckboxChecked = checkboxes.get(1).isSelected();
        Assert.assertFalse(isSecondCheckboxChecked, "Second Checkbox is checked");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}