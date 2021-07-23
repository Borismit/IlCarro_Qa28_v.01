import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void precondition(){
        //если залогинен, то разлогинься
        if (!app.userHelper().isLoggeed())//если нет кнопки Login, значит мы залогинены, тогда заходим внутрь и разлогиниваемся
        {
            app.userHelper().logout();
        }
    }
    @Test
    public void loginTestPositive(){
        //будем вызывать методы реализованные (созданные) в классе UserHalper
        //ApplicationManager хранит ссылку на userHalper, внутри userHalper доступны его методы и его родителей
        //наведя на метод (он красный, т. к. его ещё нет) openLoginForm(), создадим его (alt+enter) в классе UserHalper
        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm("noa@gmail.com","Nnoa12345$");//вызывая метод fillLoginForm() из класса UserHalper, заполняем email и password
        app.userHelper().clickYallaButton();
        app.userHelper().pause(3000); //вызываем метод pause(), чтобы хватило времени отрисовать картинку. Вызываем из класс UserHalper, а у него есть доступ к родителю class HelperBase, где сидит этот метод
        String loginS=app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));//вызываем метод getText() через класс UserHalper из его родителя class HelperBase. В этот метод отдам тот локатор,
        app.userHelper().clickOkButton();                                                                                        // по которому находим элемент, где вычитывается стринг (текст)
       Assert.assertEquals(loginS, "Logged in success");//убеждаемся, что  в //h2 лежит сообщение, что я залогинился

    }
    @Test
    //метод, который принимает объект User, которому надо залогиниться
    public void loginTestPositiveDto(){
        User user= new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$");//через "." выбираем только те поля, которые нам надо заполнить

        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm(user);
        app.userHelper().clickYallaButton();
        app.userHelper().pause(3000); //вызываем метод pause(), чтобы хватило времени отрисовать картинку. Вызываем из класс UserHalper, а у него есть доступ к родителю class HelperBase, где сидит этот метод
        String loginS=app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));//вызываем метод getText() через класс UserHalper из его родителя class HelperBase. В этот метод отдам тот локатор,
        app.userHelper().clickOkButton();                                                                                        // по которому находим элемент, где вычитывается стринг (текст)
        Assert.assertEquals(loginS, "Logged in success");//убеждаемся, что  в //h2 лежит сообщение, что я залогинился
    }
    @Test
    public void signUpPositiv(){
        User user = new User().withName("Boris").withLastName("Mitelman").withEmail("noa-326@gmail.com-7").withPassword("Nnoa12345$");

        app.userHelper().openRegistrationForm();
        app.userHelper().fillRegistrationForm(user);
        app.userHelper().clickCheckBox();
        app.userHelper().clickregistrationButton();
        app.userHelper().pause(3000);
        String SignupS = app.userHelper().getText(By.xpath("//h2[@class='message']"));
        app.userHelper().clickYallaButton();
        Assert.assertEquals(SignupS,"You are logged in success");



    }
}


