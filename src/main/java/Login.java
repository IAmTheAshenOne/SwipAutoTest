import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/** TestClass with login auto test
 * Create by Danila Polyakov
 * SWIP 2020
 */
public class Login {
    LoginPages loginPages = new LoginPages();

    AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities capabilities = new DesiredCapabilities();
    //нужно подумать над передайчей данных из функции в функцию, при нынешнем варианте пишет что драйвер is null


    @Test
    public void CorrectLogin() throws Exception {
        //apppium capabilities. От передачи капабилитей пока вынуждено отказатся
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "HUAWEI LLD-L31");
        capabilities.setCapability(MobileCapabilityType.APP, "C:\\Games\\swip_app_10.22__10_37-debug.apk");
        capabilities.setCapability("appPackage", "com.it.swip");
        capabilities.setCapability("appActivity", "com.it.swip.ui.activity.LauncherActivity");
        //starting appium server and launch an application, неудачная попытка передачи данных в метод другого класса
        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

        loginPages.ClickNextButton();
    }
}
