import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class ShopCart {


    WebDriver driver;

    @BeforeMethod

    public void SetUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

     /*
        1. Открыть браузер
        2. Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        3. В поле First Name ввести Hel
        4. В поле Last Name ввести Gj
        5. В поле Email ввести aaa@aaa.aa
        6. В поле Password ввести 1234
        7. В поле Confirm Password ввести 1234
        4. Нажать Register
        5. Проверить, что надпись "Account is created!" видна
         */




    @Test
    public void Register() {

        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Hel");
        driver.findElement(By.name("last_name")).sendKeys("Gj");
        driver.findElement(By.name("email")).sendKeys("aaa@aaa.aa");
        driver.findElement(By.name("password1")).sendKeys("1234");
        driver.findElement(By.name("password2")).sendKeys("1234");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        boolean isDisplayed1 = driver.findElement(By.className("confirmation_message")).isDisplayed();
        Assert.assertTrue(isDisplayed1);

        String email = driver.findElement(By.xpath("//*[contains(text(), 'Email')]/..//b")).getText();
        String password = driver.findElement(By.xpath("//*[contains(text(), 'Password')]/..//td[2]")).getText();

        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.cssSelector("[value = Login]")).click();
        driver.findElement(By.name("keyword")).sendKeys("White Fang");
        driver.findElement(By.cssSelector("[value=Search]")).click();
        driver.findElement(By.cssSelector("[href='./add_to_cart.py?book_id=2']")).click();
        driver.findElement(By.cssSelector("[href='./shopping_cart.py']")).click();

        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("1");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String price = driver.findElement(By.xpath("//tr[2]//td[7]")).getText();
        assertEquals(price, "10", "Price is't correct");
        }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();



    }

}
