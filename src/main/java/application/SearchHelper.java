package application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

public class SearchHelper extends HelperBase {
    public SearchHelper(WebDriver wd) {
        super(wd);
    }

    public void typeSearchCurrentMonth(String city, String dataFrom, String dataTo) {
        fillInputCity(city);
        typeInputPeriod(dataFrom, dataTo);
    }

    private void typeInputPeriod(String dataFrom, String dataTo) {
        type(By.id("dates"), dataFrom + "-" + dataTo);
        int i = (int) System.currentTimeMillis() / 1000 % 600;
        String screenshot = "src/test/screenshots/screen-" + i + ".png";//укажем путь для картинки screenshot в папку проекта screenshots (+i, чтобы картинка не перезаписывалась при новом запуске теста)
        takeScreenshot(screenshot);// вызываем метод takeScreenshot(), чтобы посмотреть, что там творится, какая ошибка, если здесь будет падение
        //можно закоментировать внизу метод click(), что бы было падение и получить в этот момент картинку screenshot
        click(By.cssSelector("div.cdk-overlay-container"));//кликаем в стороне от календаря, чтобы он схлопнулся и какбы не заслонял (хотя на самом делевизуально он не заслоняет) кликать по кнопке Y'alla
    }

    private void fillInputCity(String city) {
        type(By.id("city"), city);
        pause(500);
        //этот метод используем при выпадающем окне
        click(By.xpath("//div[@class='pac-item']"));
    }

    public boolean isListOfCarAppeared() {
        return isElementPresent(By.xpath("//div[@class='search-results']"));
    }

    public boolean isDataInPath() {
        WebElement warning = wd.findElement(By.xpath("//div[@class='error ng-star-inserted']"));//находим элемент,
        String warningTxt = warning.getText();//забираем у него текст и ложим результат в  String warningTxt
        //в течении 10 сек. будем ждать пока не появится текст на элементе warning
        new WebDriverWait(wd, 10).until(ExpectedConditions.textToBePresentInElement(warning, warningTxt));
        return warningTxt.contains("before today");//если текст будет содержать "before today", то вернёт True

    }

    public boolean buttonYallaInactiv() {
        WebElement yallaBtn = wd.findElement(By.xpath("//button[@type='submit']"));// найдём элемент (кнопку)
        return !yallaBtn.isEnabled();//метод isEnabled скажет активна кнопка или нет и вернёт True, если неактивная (т. к. есть "!") или Falls, если активная
    }

    public void fillSearchFormCurrentMonth(String city, String dataFrom, String dataTo) {
        fillInputCity(city);
        selectPeriodCurrentMonth(dataFrom, dataTo);
    }

    private void selectPeriodCurrentMonth(String dataFrom, String dataTo) {
        //07/29/2021     07/31/2021
        click(By.id("dates"));//вызываем календарь

        //не надо проверять, что это текущий месяц, т. к. он всегда текущий, проверим только даты
        String[] dataF = dataFrom.split("/");//разобьём (split) стринг dataFrom на массив по знаку "/"
        String[] dataT = dataTo.split("/");

        //dataF[1]===29  //div[text()=' 29 ']
        // dataT[1]===31  //div[text()=' 31 ']

        String dataLocatorFrom = String.format("//div[text()=' %s ']", dataF[1]);//создаём стринг, где в общей постояной неменяющейся части вместо переменной %s вставляется dataF[1]
        String dataLocatorTo = String.format("//div[text()=' %s ']", dataT[1]);

        click(By.xpath(dataLocatorFrom));
        click(By.xpath(dataLocatorTo));
    }

    public void fillSearchFormInFuture(String city, String dataFrom, String dataTo) {
        fillInputCity(city);
        //09/26/2021 - 10/30/2021
        click(By.id("dates"));//вызываем календарь

        String[] dataF = dataFrom.split("/");//разобьём (split) стринг dataFrom на массив по знаку "/"
        String[] dataT = dataTo.split("/");

        int diffStart = 0;
        //если номера текущего месяца и месяца (метод now() ), который нам надо не равны, то узнаём разницу, чтобы знать сколько раз кликать по ">", чтобы добраться до текущего месяца
        if (LocalDate.now().getMonthValue() != Integer.parseInt(dataF[0])) {
            diffStart = Integer.parseInt(dataF[0]) - LocalDate.now().getMonthValue();//разница между номерами нужного и текущего месяцами
        }
        for (int i = 0; i < diffStart; i++) {
            click(By.xpath("//button[@aria-label='Next month']"));

        }
        String dataLocatorFrom = String.format("//div[text()=' %s ']", dataF[1]);//создаём стринг, где в общей постояной неменяющейся части вместо переменной %s вставляется dataF[1]
        click(By.xpath(dataLocatorFrom));

        if (Integer.parseInt(dataF[0]) != Integer.parseInt(dataT[0])) {
            diffStart = Integer.parseInt(dataT[0]) - Integer.parseInt(dataF[0]);
        }
        for (int i = Integer.parseInt(dataF[0]); i < diffStart; i++) {
            click(By.xpath("//button[@aria-label='Next month']"));

        }
        String dataLocatorTo = String.format("//div[text()=' %s ']", dataT[1]);
        click(By.xpath(dataLocatorTo));
      }
    }
