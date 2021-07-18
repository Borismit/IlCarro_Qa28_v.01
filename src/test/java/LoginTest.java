import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


    @Test
    public void loginTestPositive(){

      click(By.xpath("//a[.=' Log in ']"));

      type(By.id("email"), "noa@gmail.com");
      type(By.id("password"), "Nnoa12345$");
      //click(By.xpath("//*[text()='Y’alla!']"));
       pause(3000); //вызываем метод pause(), чтобы хватило времени отрисовать картинку
        click(By.xpath("//*[@type='submit']"));//кликаем на кнопку Y’alla! (это вместо верхней строчки)
       // String loginS=wd.findElement(By.xpath("//div[@class='dialog-container']//h2")).getText();
        String loginS=getText(By.xpath("//div[@class='dialog-container']//h2"));//вызываем метод getText(), в этот метод отдам тот локатор,
                                                                              // по которому находим элемент, где вычитывается стринг (текст)
        click(By.xpath("//button[.='Ok']"));
       Assert.assertEquals(loginS, "Logged in success");//убеждаемся, что  в //h2 лежит сообщение, что я залогинился

    }
}
