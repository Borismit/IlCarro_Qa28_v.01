package application;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarHelper extends HelperBase{
    //создадим конструктор от суперкласса HelperBase, чтобы HelperBase стал его наследником: ставим курсор на строчку HelperBase->alt+enter-> Grate constractor ...
    public CarHelper(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        click(By.id("1"));
    }

    public void fillCarForm(Car car) {
        if(isCarFormPresent()){
            typeLocation(car.getAddress());//в поле Location из объекта car заберём Address (см. метод ниже)
            type(By.id("make"), car.getMake());
            type(By.id("model"), car.getModel());
            type(By.id("year"), car.getYear());
            type(By.id("engine"), car.getEngine());

            select(By.id("fuel"), car.getFuel());
            select(By.id("gear"), car.getGear());
            select(By.id("wheelsDrive"), car.getWD());

            type(By.id("doors"), car.getDoors());
            type(By.id("seats"), car.getSeats());
            type(By.id("class"), car.getClasS());

            type(By.id("fuelConsumption"), car.getFuelConsumption());
            type(By.id("serialNumber"), car.getCarRegNumber());//серийный номер должен быть уникальным, второй раз тест с тем же номером не пройдёт, надо его менять
            type(By.id("price"), car.getPrice());
            type(By.id("distance"), car.getDistanceIncluded());
            type(By.cssSelector(".feature-input"), car.getTypeFeature());
            type(By.id("about"), car.getAbout());

            pause(7000);

        }

    }
    //этот метод используем при выпадающем окне
    private void typeLocation(String address) {
        WebElement el = wd.findElement(By.id("pickUpPlace"));
        el.click();
        el.clear();
        el.sendKeys(address);
        pause(2000);
        wd.findElement(By.xpath("//div[@class='pac-item']")).click();//находим и кликаем первый элемент (он всегда совпадает с полностью напечатанным в поле) в выпадающем окне
        pause(2000);

    }

    private boolean isCarFormPresent() {
        return  wd.findElements(By.xpath("//h1[contains(.,'Let the car work')]")).size()>0;//если найден элемент (надпись) Let the car work, возвращается true, т. е. форма открылась
    }

    public void attachPhoto() {
//находим по id("photos") кнопку Add photos of your car, т. к. этот элемент относится к типу input, то можно использовать, как и для ввода текста, метод sendKeys(), в нём указываем путь к картинке
        wd.findElement(By.id("photos")).sendKeys("C:\\Users\\97254\\Desktop\\Programming courses\\LESSONS\\Lessons Qa\\Lesson_32/bmw.jpeg");

    }

    public boolean isCarAdded() {
        String text
                = wd.findElement(By.xpath("//div[@class='dialog-container']//h1")).getText();
        return text.contains("Car added");
    }

    public void clickButtonSubmit() {
        new WebDriverWait(wd, 10)
                .until(ExpectedConditions.elementToBeClickable(wd.findElement(By.xpath("//button[text()='Submit']"))));
        click(By.xpath("//button[.='Submit']"));
    }
}
