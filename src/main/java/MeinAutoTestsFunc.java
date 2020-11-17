import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;

/**
 * Класс, содержащий основные взаимодействия для приложения
 */
public class MeinAutoTestsFunc {
    AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities capabilities = new DesiredCapabilities();
        //нужно подумать над передайчей данных из функции в функцию, при нынешнем варианте пишет что драйвер is null

    /**
     * первая аутентификация, с вводом номера телефона, подтверждением по смс и etc
     */
    public void CorrectLogin() throws Exception {
        //appium capabilities от передачи капабилитей пока вынуждено отказатся, как и от page objects
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "HUAWEI LLD-L31");
        capabilities.setCapability(MobileCapabilityType.APP, "C:\\Games\\swip_app_11.06__12_26-debug.apk");
        capabilities.setCapability("appPackage", "com.it.swip");
        capabilities.setCapability("appActivity", "com.it.swip.ui.activity.LauncherActivity");
        //  capabilities.setCapability( "noReset", "true");
        //starting appium server and launch an application, неудачная попытка передачи данных в метод другого класса
        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
        }
        driver.findElementById("com.it.swip:id/welcome_next").click();
        driver.findElementById("com.it.swip:id/phone_edit").sendKeys("72222222222");
        driver.findElementById("com.it.swip:id/next_button").click();
        driver.findElementById("com.it.swip:id/code_edit").sendKeys("6666");
        for ( int i=1; i < 5; i++ ) {
            driver.findElementById("com.it.swip:id/pin_1").click();
        }
        driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        driver.findElementById("com.it.swip:id/close").click();
        }

    /**
     * Добавление новой платежной карты через профиль
     */
    public void CreateNewCard() {
        //Проверяем, что находимся на приветственном экране
        driver.findElementById("com.it.swip:id/item5im").isDisplayed();
        driver.findElementById("com.it.swip:id/item5im").click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/payment_methods")));
        driver.findElementById("com.it.swip:id/payment_methods").click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Добавить карту']")).click();
        driver.findElementById("com.it.swip:id/card_num_edittext").sendKeys("4242 4242 4242 4242");
        driver.findElementById("com.it.swip:id/card_date_edittext").sendKeys("10/50");
        driver.findElementById("com.it.swip:id/card_cvv_edittext").sendKeys("123");
        driver.findElementById("com.it.swip:id/next_button").click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementsByClassName("android.widget.EditText").get(0)));
        driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys("4");
        driver.findElementsByClassName("android.widget.Button").get(0).click();
    }

    /**
     * Подключение СТП при помощи ползунка на главном экране
     * пока не полностью автоматизировано
     * сканирование все еще необходимо проводить
     */
    public void StpConnect() throws Exception{
        driver.findElementById("com.it.swip:id/stp_switch").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementById("com.it.swip:id/update_details").click();
        //  driver.findElementById("com.it.swip:id/start_stp_flow_btn").click();
        driver.findElementById("com.it.swip:id/next_button").click();
        try {
            driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        }
        catch (NoSuchElementException exception) {
            driver.findElementById("com.it.swip:id/okcancelview").isDisplayed();
        }
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/okcancel")));
        driver.findElementById("com.it.swip:id/okcancel").click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/user_last_name")));
        driver.findElementById("com.it.swip:id/user_last_name").sendKeys("Тесто");
        driver.findElementById("com.it.swip:id/user_first_name").sendKeys("Тест");
        driver.findElementById("com.it.swip:id/user_middle_name").sendKeys("Тест");
        driver.findElementById("com.it.swip:id/make_passport_photo").click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/next_button")));
        driver.findElementById("com.it.swip:id/next_button").click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/next_button")));
        driver.findElementById("com.it.swip:id/next_button").click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/stp_header")));
        driver.findElementById("com.it.swip:id/next_button").click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/item1im")));
       //TODO разобратся с селфи
    }

    /**
     * Метод для добавления дисконтной карты через раздел "Карты"
     */
    public void AddNewDiscountCard() throws Exception{
            driver.findElementById("com.it.swip:id/item4im").click();
            driver.findElementById("com.it.swip:id/menu_add").click();
            driver.findElementsByClassName("android.widget.ImageView").get(0).click();
          try {
              driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
          }
          catch (WebDriverException exception){
              driver.findElementById("com.it.swip:id/manual").isDisplayed();
          }
            driver.findElementById("com.it.swip:id/manual").click();
            driver.findElementById("com.it.swip:id/drop_down_search").sendKeys("Swip");
            driver.findElementById("com.it.swip:id/discountcard_number").sendKeys("123");
            driver.findElementById("com.it.swip:id/next_button").click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/action_search")));
            driver.findElementById("com.it.swip:id/action_search").isDisplayed();
    }

    /**
     * Удаление скидочной карты, не рекомендуется использовать без создания
     */
    public void DeleteCreatedDiscountCard() throws Exception {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + "Swip" + "\").instance(0))").click();
            driver.findElementById("com.it.swip:id/menu_delete").click();
            driver.findElementById("android:id/button1").click();
            //проверяем что попали в раздел карты
            driver.findElementById("com.it.swip:id/action_search").isDisplayed();
    }

    /**
     * Аутентификация без номера телефона, just enter pin-code
     */
    public void AuthWithoutPhoneConfirmation () throws Exception {
        //apppium capabilities от передачи капабилитей пока вынуждено отказатся
        //TODO выяснить, можно ли передавать капабилити в другие классы
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "HUAWEI LLD-L31");
        capabilities.setCapability(MobileCapabilityType.APP, "C:\\Games\\swip_app_11.06__12_26-debug.apk");
        capabilities.setCapability("appPackage", "com.it.swip");
        capabilities.setCapability("appActivity", "com.it.swip.ui.activity.LauncherActivity");
        capabilities.setCapability( "noReset", "true");
        capabilities.setCapability("systemPort", "8200");
        //starting appium server and launch an application, неудачная попытка передачи данных в метод другого класса
        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

            driver.findElementById("com.it.swip:id/pin_title_tw").isDisplayed();
        for ( int i=1; i < 5; i++ ) {
            driver.findElementById("com.it.swip:id/pin_1").click();
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/close")));
            driver.findElementById("com.it.swip:id/close").click();
        }
        catch (NoSuchElementException exception){
            driver.findElementById("com.it.swip:id/item1im").isDisplayed();
        }
    }

    /**
     * Метод для удаления платежной карты
     * Не рекомендуется запускать без создания платежной карты
     * */
    public void DeletePaymentCard() throws Exception {
            driver.findElementById("com.it.swip:id/item5im").click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/tv_app_protection_status")));
            driver.findElementById("com.it.swip:id/payment_methods").click();
            driver.findElementsByClassName("android.widget.RadioButton").get(0).click();
            driver.findElementsByClassName("android.widget.ImageView").get(3).click();
            driver.findElementById("com.it.swip:id/title").click();
    }

    /**
     * Метод для проведения успешной предоплаты
     * */
    public void SuccesfulPreorder() throws Exception {
            driver.findElementById("com.it.swip:id/item2im").click();
            sleep(5000);
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]").click();
            driver.findElementById("com.it.swip:id/ma_preorder_btn").click();
           // Todo необходимо допилить когда восстановятся предзаказы

    }

    /**
     * Метод для добавления Дисконтной карты через раздел Скидки
     * перед запуском убедится, что карточек нет
     */
    public void AddDiscountCardWithSalesSection() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/item2im")));
        driver.findElementById("com.it.swip:id/item2im").click();
        sleep(5000);
        driver.findElementsByClassName("android.view.View").get(3).click();
        sleep(5000);
        //это скролл и поиск нужного элемента, затем нажатие на него
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + "Выпустить карту лояльности" + "\").instance(0))").click();
        try {
            driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys("Тесто");
            driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys("тест");
            driver.findElementsByClassName("android.widget.EditText").get(2).sendKeys("Тест");
            driver.findElementsByClassName("android.widget.EditText").get(3).click();
            driver.findElementById("com.it.swip:id/dp_ok").click();
            driver.findElementsByClassName("android.widget.EditText").get(4).sendKeys("Rem@ram.r");
            driver.hideKeyboard();
            driver.findElementById("com.it.swip:id/emit_card_details_proceed_btn").click();
        }
        catch (IndexOutOfBoundsException exception) {
            driver.findElementsByClassName("android.widget.ImageButton").get(0).click();
        }
    }

    /**
     * Вспомогательный метод, проверка существаования скидочной карточки для магазина SWIP.
     * Необходим для выполнения теста по удалению карточки магазина
     * вызывать только в обработчике исплючений.
     */
    public void CheckDiscountCard () throws Exception {

        driver.findElementById("com.it.swip:id/item4").click();
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + "Swip" + "\").instance(0))").isDisplayed();
        }
        catch (NoSuchElementException exception) {
            throw new NoSuchElementException("карточка отсутствует");
        }
    }

    /**
     * Вспомогательный метод, проверка существаования платежной карточки.
     * Необходим для выполнения теста по удалению карточки
     * вызывать только в обработчике исплючений.
     */
    public void CheckPaymentCard () throws Exception {
        driver.findElementById("com.it.swip:id/item5im").click();
        try {
            sleep(2000);
            driver.findElementsByClassName("android.widget.RelativeLayout").get(0).isDisplayed();
        }
        catch (NoSuchElementException exception) {
            throw new NoSuchElementException("Карточка отсутствует");
        }
    }

    /**
     * Вспомогательный метод для проверки существования карточки, созданной для магазина
     * (отличается от CheckDiscountCard() тем, что проверяем через раздел скидок.
     */
    public void CheckDiscountCardThroughSalesSection() throws Exception {
        driver.findElementById("com.it.swip:id/item2im").click();
        sleep(5000);
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + "SWIP SHOP" + "\").instance(0))").click();
       // driver.findElementsByClassName("android.view.View").get(3).click();
        sleep(5000);
        //это скролл и поиск нужного элемента, затем нажатие на него
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + "Выпустить карту лояльности" + "\").instance(0))").isDisplayed();
        }
        //значит карточка для этого магазина существует
        catch (NoSuchElementException exception) {
                throw new NoSuchElementException("Карточка отсутствует");
        }
        finally {
            driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Перейти вверх\"]").click();
            // driver.findElementsByClassName("android.widget.ImageButton").get(0).click();
        }
    }

    /**
     * Метод для удаления карточки через раздел скидок
     */
    public void DeleteDiscountCardThroughSales() throws Exception {
        driver.findElementById("com.it.swip:id/item2im").click();
       // driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + "SWIP SHOP" + "\").instance(0))").click();
        //TODO тест не стабилен, так как нельзя определить магазин по тексту
        driver.findElementsByClassName("android.view.View").get(4).click();
        try {
            driver.findElementById("com.it.swip:id/discount_card_image").click();
            driver.findElementById("com.it.swip:id/menu_delete").click();
            driver.findElementById("android:id/button1").click();
        }
        catch (NoSuchElementException exception){
            throw new NoSuchElementException("карточка не Exist");
        }
        finally {
            driver.findElementsByClassName("android.widget.ImageButton").get(0).click();
        }
    }

    /**
     * Метод для смены пароля
     * на самом деле пароль не меняется
     */
    public void ChangePinCode () throws Exception {
        driver.findElementById("com.it.swip:id/item5im").click();
        driver.findElementById("com.it.swip:id/tv_app_protection_status").click();
        for (int i=0; i<2;i++) {
            driver.findElementById("com.it.swip:id/protection_pin").click();
        }
        for (int i=0; i<4; i++){
            driver.findElementById("com.it.swip:id/pin_1").click();
        }
        driver.findElementById("com.it.swip:id/pin_title_tw").isDisplayed();
        for (int i=0; i<4; i++){
            driver.findElementById("com.it.swip:id/pin_1").click();
        }
        driver.findElementById("com.it.swip:id/text1").isDisplayed();
    }

    /**
     * Метод для просмотра Сэкономленной статистики.
     */
    //TODO возможно следует добавить подсчет денег
    public void CheckSavedMoney() {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + "Сэкономлено за всё время" + "\").instance(0))").click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/toolbar_layout")));
        driver.findElementsByClassName("android.widget.RelativeLayout").get(0).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementsByClassName("android.widget.TextView").get(0)));
        driver.findElementById("com.it.swip:id/menu_barcode").click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/image")));
        driver.findElementById("com.it.swip:id/close").click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementsByClassName("android.widget.TextView").get(0)));
        driver.findElementByAccessibilityId("Перейти вверх").click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementsByClassName("android.widget.RelativeLayout").get(0)));
    }

    /**
     * Метод для оплаты по QR коду, скнирование не работает
     */
    public void QRPayment () throws Exception {
        driver.findElementById("com.it.swip:id/item3").click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/qr_description")));
        //сканироание qr кода
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/next_button")));
        driver.findElementById("com.it.swip:id/next_button").click();
        try {
            driver.findElementById("android:id/button2").click();
            driver.findElementById("com.it.swip:id/choose_card_container").click();
            driver.findElementsByClassName("android.widget.TextView").get(0).click();
        }
        catch (NoSuchElementException exception){
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/textView3")));
        }
        driver.findElementById("com.it.swip:id/start_3").click();
        driver.findElementById("com.it.swip:id/success_comment_et").click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/feedback_message")));
        driver.findElementById("com.it.swip:id/feedback_message").sendKeys("автотест");
        driver.findElementById("com.it.swip:id/feedback_send_button").click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/item1im")));
    }
    public void CheckDiscountsThroughBanner() throws Exception {
       String bannerText = driver.findElementById("com.it.swip:id/title").getText();
       driver.findElementById("com.it.swip:id/title").click();
       driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + bannerText + "\").instance(0))").click();
       //TODO а что тут делать вообще? стопаем
    }

}







