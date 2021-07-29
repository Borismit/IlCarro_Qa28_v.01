package application;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;


public class HelperBase {
    WebDriver wd;
    //сгенерируем конструктор: Ganerate->constractor->wd:WebDraiver,
    //который даёт классу HelperBase, как родителю, диктовать каким образом создавать экземпляры дочерних классов
    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }//это конструктор

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
    public void select(By locator, String option){
        //new Select(wd.findElement(By.id("fuel"))).selectByIndex(1);//выбираем или по порядковому номеру
        new Select(wd.findElement(locator)).selectByValue(option);//или по значению Value
        //new Select(wd.findElement(By.id("fuel"))).selectByVisibleText("");//или по видимому тексту между тегами
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

    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size()>0;//по локатору (locator) ищем элемент и если size()>0, то этот элемент есть
    }

    public void takeScreenshot(String pathToFile){
        File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        File screenshot = new File((pathToFile));//виерхний фаил переписываем в этот фаил
        try {
            Files.copy(tmp,screenshot);//из файла tmp скопируй в этот фаил screenshot для сохранения
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


