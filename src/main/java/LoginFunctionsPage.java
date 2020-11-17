import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Class with authentication methods
 * Не работает пока что, пишет что this.driver is null (WHAT?)
 */

public class LoginFunctionsPage {
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
        for (int i = 1; i < 5; i++) {
            driver.findElementById("com.it.swip:id/pin_1").click();
        }
        driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        driver.findElementById("com.it.swip:id/close").click();
    }

    /**
     * Аутентификация без номера телефона, just enter pin-code
     */
    public void AuthWithoutPhoneConfirmation() throws Exception {
        //apppium capabilities от передачи капабилитей пока вынуждено отказатся
        //TODO выяснить, можно ли передавать капабилити в другие классы
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "HUAWEI LLD-L31");
        capabilities.setCapability(MobileCapabilityType.APP, "C:\\Games\\swip_app_11.06__12_26-debug.apk");
        capabilities.setCapability("appPackage", "com.it.swip");
        capabilities.setCapability("appActivity", "com.it.swip.ui.activity.LauncherActivity");
        capabilities.setCapability("noReset", "true");
        //starting appium server and launch an application, неудачная попытка передачи данных в метод другого класса
        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

        driver.findElementById("com.it.swip:id/pin_title_tw").isDisplayed();
        for (int i = 1; i < 5; i++) {
            driver.findElementById("com.it.swip:id/pin_1").click();
        }
    }
}









