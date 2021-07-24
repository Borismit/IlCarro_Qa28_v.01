package application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    UserHelper userHalper;//объявляем userHalper и ниже инициализируем его
    CarHelper carHelper;

  public void init(){
      wd = new ChromeDriver();
      wd.manage().window().maximize();
      wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      wd.navigate().to("https://ilcarro.xyz/search");
     userHalper = new UserHelper(wd);//инициализируем userHalper
      carHelper = new CarHelper(wd);
  }

  public void stop(){
  wd.quit();
  }
    //сгенерируем гетер по полю UserHalper, чтобы метод (поле) UserHalper был доступен (потом getUserHalper переименуем для краткости в userHalper ) его наследникам
    public UserHelper userHelper() {
        return userHalper;
    }

    public CarHelper carHelper() {
        return carHelper;
    }
}
