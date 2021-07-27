import models.Car;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewcarTest extends TestBase{
    @BeforeMethod
    public void precondition(){
        if(app.userHelper().isLogged()){
            app.userHelper().login(new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$"));
        }
    }


    @Test
    public void createNewCar(){
        int i = (int) ((System.currentTimeMillis()/1000)%3600);//добавляем с каждым тестом i, чтобы менялся номер машины (carRegNumber("12"+i)), см. ниже
        System.out.println(i);//распечатаем для интереса i
     //создадим автомобиль
        Car car = Car.builder()
                .address("Tel Aviv, Israel")
                .make("BMW")
                .model("M5")
                .year("2020")
                .engine("2.0")
                .fuel("Petrol")
                .gear("MT")
                .wD("AWD")
                .doors("5")
                .seats("4")
                .clasS("C")
                .fuelConsumption("6.5")
                .carRegNumber("11"+i)//с каждым тестом меняем номер машины, т. к. он должен быть уникальный
                .price("65")
                .distanceIncluded("500")
                .typeFeature("type of")
                .about("Very good car")
                .build();

        System.out.println("Car Number: ... " + car.getCarRegNumber());//распечатаем для интереса номер машины

        app.carHelper().openCarForm();
        app.carHelper().fillCarForm(car);
        app.carHelper().attachPhoto();
        app.userHelper().pause(10000);
        app.carHelper().clickButtonSubmit();


        //assert
        Assert.assertTrue(app.carHelper().isCarAdded());//если вернётся true, то всё прошло

    }
}


//button[.='Submit'] - загружай!
//label[.='Add photos of your car'] - добавить фото
//mat-chip - текст - знак, что загрузилось
//button[.='Show car'] - показать машину
//img[@class='ng-star-inserted'] - появилась картинка
//a[.='Delete account'] - удалить акаунт