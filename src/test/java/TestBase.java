import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver wd;

    @BeforeClass
    public void init(){
        wd=new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.xyz/search");
    }

    @AfterClass
    public void tearDown(){
        wd.quit();
    }

    public void click(By locator){
        wd.findElement(locator).click();
    }
    public  void type(By locator, String text){
        if (text!=null) {
            WebElement element = wd.findElement(locator);
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }
//метод, который будет вычитывать текст из элемента и возвращать
    public String getText(By locator){
        return wd.findElement(locator).getText();
    }
    //пишем метод pause(), чтобы хватило времени отрисовать картинку
     public void pause(int millisec){
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
