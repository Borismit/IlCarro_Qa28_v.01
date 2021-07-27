package application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    UserHelper userHalper;//объявляем userHalper и ниже инициализируем его
    CarHelper carHelper;//объявляем CarHelper и ниже инициализируем егои ниже инициализируем его и ниже инициализируем его

  public void init(){
      wd = new ChromeDriver();
      wd.manage().window().maximize();
      wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      wd.navigate().to("https://ilcarro.xyz/search");
     userHalper = new UserHelper(wd);//инициализируем userHalper
      carHelper = new CarHelper(wd);//инициализируем CarHelper
  }

  public void stop(){
  //wd.quit();
  }
    //сгенерируем гетер по полю UserHalper, чтобы метод (поле) UserHalper был доступен (потом getUserHalper переименуем для краткости в userHalper ) его наследникам
    public UserHelper userHelper() {
        return userHalper;
    }

    public CarHelper carHelper() {
      //сгенерируем гетер по полю carHelper;, чтобы метод (поле) carHelper; был доступен (потом getUserHalper переименуем для краткости в carHalper ) его наследникам
      return carHelper;
    }
}
