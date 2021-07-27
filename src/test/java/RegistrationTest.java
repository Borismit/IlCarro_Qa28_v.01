import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {

    @BeforeMethod
    public void precondition(){
        //если залогинен, то разлогинься
        if (!app.userHelper().isLogged())//если нет кнопки Login, значит мы залогинены, тогда заходим внутрь и разлогиниваемся
        {
            app.userHelper().logout();
        }
    }

    @Test
    public void registrationTest(){
        User user = new User().withName("Boris").withLastName("Mitelman").withEmail("noa@gmail.com-7").withPassword("Nnoa12345$");

        app.userHelper().openRegistrationForm();
        app.userHelper().fillRegistrationForm(user);
        app.userHelper().clickCheckBox();
        app.userHelper().pause(3000);
        app.userHelper().clickregistrationButton();
        app.userHelper().pause(3000);
        String SignupS = app.userHelper().getText(By.xpath("//h2[@class='message']"));
        app.userHelper().clickYallaButton();
        Assert.assertEquals(SignupS,"You are logged in success");

    }

}
