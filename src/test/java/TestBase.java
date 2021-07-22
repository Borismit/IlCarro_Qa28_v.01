import application.ApplicationManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {
protected static ApplicationManager app = new ApplicationManager();//ссылка на класс ApplicationManager, protected - чтобы ApplicationManager был доступен только здесь
                                                                   //static - чтобы ApplicationManager одир раз создался (прогнался). Все методы в классе ApplicationManager теперь доступны


    @BeforeClass
    public void start(){
     app.init(); //обращаемся к методу init() в классе ApplicationManager

    }

    @AfterClass
    public void tearDown(){
      app.stop();//обращаемся к методу stop() в классе ApplicationManager
    }


}
