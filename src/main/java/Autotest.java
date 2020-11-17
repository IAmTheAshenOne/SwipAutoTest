import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/** Class with AutoTests
 * Create by Danila Polyakov
 * SWIP 2020
 */

public class Autotest {
     MeinAutoTestsFunc MainFunctions = new MeinAutoTestsFunc();
     LoginFunctionsPage Login = new LoginFunctionsPage();
     StpConnectPage STP = new StpConnectPage();
    //Setting driver
    AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    @Test
    public void AddingPaymentCard() throws Exception {
        MainFunctions.AuthWithoutPhoneConfirmation();
        MainFunctions.CreateNewCard();
    }

    @Test
    public void LoginWithPhoneAuth () throws Exception{
        MainFunctions.CorrectLogin();
    }

    @Test
    public void CreateDiscountCard() throws Exception{
        MainFunctions.AuthWithoutPhoneConfirmation();
        MainFunctions.AddNewDiscountCard();
    }

    @Test
    public void DeleteDiscountCard() throws Exception{
        MainFunctions.AuthWithoutPhoneConfirmation();
        //проверка на наличие скидочной карты (если нет то создать)
        try {
            MainFunctions.CheckDiscountCard();
        }
        catch (NoSuchElementException exception) {
            MainFunctions.AddNewDiscountCard();
        }
        MainFunctions.DeleteCreatedDiscountCard();
    }

    @Test
    public void DeletePaymentCard() throws Exception {
        MainFunctions.AuthWithoutPhoneConfirmation();
        try {
            MainFunctions.CheckPaymentCard();
        }
        catch (NoSuchElementException Exception){
            MainFunctions.CreateNewCard();
        }
        MainFunctions.DeletePaymentCard();
    }


    public void SuccessfulPreorder() throws Exception {
        MainFunctions.AuthWithoutPhoneConfirmation();
        MainFunctions.SuccesfulPreorder();
    }

    @Test
    public void CreateDiscountCardThroughTheDiscounts () throws Exception {
        MainFunctions.AuthWithoutPhoneConfirmation();
        try {
            MainFunctions.CheckDiscountCardThroughSalesSection();
        }
        catch (NoSuchElementException exception) {
            MainFunctions.DeleteDiscountCardThroughSales();
        }
        MainFunctions.AddDiscountCardWithSalesSection();
    }

    @Test
    public void ChangePinCode() throws Exception {
        MainFunctions.AuthWithoutPhoneConfirmation();
        MainFunctions.ChangePinCode();
    }

    @Test
    public void CheckSavedCash() throws Exception {
        MainFunctions.AuthWithoutPhoneConfirmation();
        MainFunctions.CheckSavedMoney();
    }


    public void StpConnect() throws Exception {
        MainFunctions.AuthWithoutPhoneConfirmation();
        MainFunctions.StpConnect();
    }


   public void SuccessfulQRPayment() throws Exception {
        MainFunctions.AuthWithoutPhoneConfirmation();
        MainFunctions.QRPayment();
    }


    public void CheckDiscountsThroughBanner() throws Exception {
        MainFunctions.AuthWithoutPhoneConfirmation();
        MainFunctions.CheckDiscountsThroughBanner();
    }

}



