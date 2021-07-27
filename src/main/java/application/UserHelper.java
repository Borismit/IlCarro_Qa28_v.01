package application;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase {
    //передадим (создадим) конструктор от суперкласса HelperBase, чтобы HelperBase стал его наследником: ставим курсор на строчку HelperBase->alt+enter-> Grate constractor ...
    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a[.=' Log in ']"));//кликаем на кнопку Login
    }
    public void fillLoginForm(String email, String password){
        type(By.id("email"),email);
        type(By.id("password"),password);
    }
    public void fillLoginForm(User user){
        type(By.id("email"),user.getEmail());
        type(By.id("password"),user.getPassword());
    }
    public void clickYallaButton(){
        click(By.xpath("//*[@type='submit']"));//кликаем на кнопку Y’alla! (кнопка login)
    }

    public void clickOkButton() {
        click(By.xpath("//button[.='Ok']"));
    }

    public boolean isLogged() {
        return wd.findElements(By.xpath("//a[.=' Log in ']")).size()>0;//если размер списка findElements >0, то элемент (кнопка) Log in есть,
                                                                       //если <0, то её нет и вернётся не ошибка а falls
    }

    public void logout() {
        click(By.xpath("//a[.=' Logout ']"));

    }

    public void openRegistrationForm() {
        click(By.xpath("//a[.=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void clickCheckBox() {
        click(By.xpath("//label[@class='checkbox-label terms-label']"));
    }

    public void clickregistrationButton() {
        click(By.xpath("//*[@type='submit']"));
    }

    public void login(User user) {
        click(By.xpath("//a[.=' Log in ']"));
        type(By.id("email"),user.getEmail());
        type(By.id("password"),user.getPassword());
        click(By.xpath("//*[@type='submit']"));
        click(By.xpath("//button[.='Ok']"));
        pause(2000);
    }

//    public void clickYallaButton() {
//        click(By.xpath("//button[.='Ok']"));
//    }
}
