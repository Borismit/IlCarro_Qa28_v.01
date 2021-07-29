import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends TestBase{

    @Test
    public void positiveTestSendKey(){
        //witch concantinate string - передадим в этот метод два стринга: дата от и дата до
        app.search().typeSearchCurrentMonth("Hifa","07/27/2021","07/30/2021");//выберем текущий месяц и натайпим туда
        app.userHelper().clickYallaButton();
       app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isListOfCarAppeared());//если вернёт True, значит список машин с их фото отобразился - тест пройден
    }

    @Test
    public void negativeTestSendKey(){
        //witch concantinate string - передадим в этот метод два стринга: дата от и дата до
        app.search().typeSearchCurrentMonth("Hifa","06/29/2021","06/31/2021");//выберем текущий месяц и натайпим туда

        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isDataInPath());//правда ли, что появляется текст, что не можем выбрать прошлую дату
        Assert.assertTrue(app.search().buttonYallaInactiv());//правда ли, что кнопка Yalla не активна
    }

    @Test
    public void selectPeriolCurrentMonth(){
       app.search().fillSearchFormCurrentMonth("Hifa","07/29/2021","07/31/2021");
        app.userHelper().clickYallaButton();
        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isListOfCarAppeared());//если вернёт True, значит список машин с их фото отобразился - тест пройден

    }

    @Test
    public void selectPeriodFuture(){
        app.search().fillSearchFormInFuture("Hifa","09/26/2021","10/30/2021");
        app.carHelper().pause(5000);
        app.userHelper().clickYallaButton();
        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isListOfCarAppeared());//если вернёт True, значит список машин с их фото отобразился - тест пройден

    }

}
