import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShopCart {


    WebDriver driver;

    @BeforeMethod

    public void SetUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
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

        driver.get("https://sharelane.com/cgi-bin/show_book.py?book_id=2");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.cssSelector("[value = Login]")).click();
        //boolean isDisplayed2 = driver.findElement(By.xpath("//span[@class='user']")).isDisplayed();
        //Assert.assertTrue(isDisplayed2);

        driver.get("https://sharelane.com/cgi-bin/show_book.py?book_id=2");

        driver.findElement(By.xpath("/html/body/center/table/tbody/tr[5]/td/table/tbody/tr/td[2]/p[2]/a")).click();



    }

}
