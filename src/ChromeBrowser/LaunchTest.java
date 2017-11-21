package ChromeBrowser;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

public class LaunchTest {

    private WebDriver driver;
    private JavascriptExecutor js;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
        driver = new ChromeDriver();

        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testPageGet() {
        //get page
        driver.get("http://timvroom.com/selenium/playground/");

        //step 1 get title
        String title = driver.getTitle();
        WebElement answer1 = driver.findElement(By.id("answer1"));
        answer1.sendKeys(title);

        //step 2 set name
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys("Kilgore Trout");

        //step 3 set occupation
        WebElement select = driver.findElement(By.id("occupation"));
        Select dropDown = new Select(select);
        dropDown.selectByVisibleText("Science Fiction Author");

        //step 4 count blue boxes
        String boxes = js.executeScript("return boxes;").toString();
        WebElement answer4 = driver.findElement(By.id("answer4"));
        answer4.sendKeys(boxes);

        //step 5 click me
        driver.findElement(By.linkText("click me")).click();

        //step 6 find red box class
        WebElement red = driver.findElement(By.id("redbox"));
        WebElement answer6 = driver.findElement(By.id("answer6"));
        answer6.sendKeys(red.getAttribute("class"));

        //step 7 js function
        js.executeScript("ran_this_js_function()");

        //step 8 js function
        WebElement answer8 = driver.findElement(By.id("answer8"));
        String ans = (js.executeScript("return got_return_from_js_function();")).toString();
        answer8.sendKeys(ans);

        //step 9
        driver.findElement(By.name("wrotebook")).click();

        //step 10 text from red
        WebElement answer10 = driver.findElement(By.id("answer10"));
        answer10.sendKeys(red.getText());

        //step 11 box placement
        WebElement answer11 = driver.findElement(By.id("answer11"));
        String order = js.executeScript("return box_order[0];").toString();
        if (order.equals("green")) {
            answer11.sendKeys("green");
        }
        else answer11.sendKeys("orange");

        //step 12 set width
        driver.manage().window().setSize(new Dimension(850, 650));

        //step 13 yes if id ishere is here
        WebElement answer13 = driver.findElement(By.id("answer13"));
        boolean exists = driver.findElements(By.id("ishere")).size() != 0;
        if (exists) {
            answer13.sendKeys("yes");
        }
        else {
            answer13.sendKeys("no");
        }

        //step 14 yes if purple
        WebElement answer14 = driver.findElement(By.id("answer14"));
        boolean purple = driver.findElement(By.id("purplebox")).isDisplayed();
        if (purple) {
            answer14.sendKeys("yes");
        }
        else {
            answer14.sendKeys("no");
        }

        //step 15 click then wait
        driver.findElement(By.linkText("click then wait")).click();
        WebElement waiter = (new WebDriverWait(driver, 10))
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .until(ExpectedConditions.elementToBeClickable(By.linkText("click after wait")));
        driver.findElement(By.linkText("click after wait")).click();

        //step 16 click OK
        driver.switchTo().alert().accept();

        //step 17 click submit
        driver.findElement(By.id("submitbutton")).click();
    }


}