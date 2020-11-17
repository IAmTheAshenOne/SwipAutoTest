import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public class StpConnectPage {
    AndroidDriver<AndroidElement> driver = null;
    //нужно подумать над передайчей данных из функции в функцию, при нынешнем варианте пишет что драйвер is null
    DesiredCapabilities capabilities = new DesiredCapabilities();
    public void DriverConnect() throws Exception{
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
    }


}
