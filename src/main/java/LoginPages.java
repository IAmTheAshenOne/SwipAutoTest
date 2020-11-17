import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginPages {
    private AndroidDriver<AndroidElement> driver;

    public LoginPages(){
    }
   public LoginPages(AndroidDriver<AndroidElement> driver){
        this.driver = driver;
       PageFactory.initElements(new AppiumFieldDecorator(driver),this);
   }
   @AndroidFindBy (id = "com.it.swip:id/welcome_next")
   private AndroidElement WelkomeNextButton;

    public void ClickNextButton (){
    WelkomeNextButton.click();
    }

    public void login (String phoneNumber, String PinCode){
    }


/*

        public void CorrectLogin() throws Exception {
            //apppium capabilities от передачи капабилитей пока вынуждено отказатся, как и от page objects
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
        public void CreateNewCard(){
            driver.findElementById("com.it.swip:id/item5im").click();

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("com.it.swip:id/payment_methods")));

            driver.findElementById("com.it.swip:id/payment_methods").click();
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.TextView").click();
          //  driver.findElementById("ad3e10e7-7a1b-4ac9-9386-cf5f3b6d98c9").click();
            driver.findElementById("com.it.swip:id/card_num_edittext").sendKeys("4242 4242 4242 4242");
            driver.findElementById("com.it.swip:id/card_date_edittext").sendKeys("10/50");
            driver.findElementById("com.it.swip:id/card_cvv_edittext").sendKeys("123");
            driver.findElementById("com.it.swip:id/next_button").click();
            */

        }





