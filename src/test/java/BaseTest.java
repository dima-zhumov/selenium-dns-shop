import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    protected static String baseUrl;
    public static Properties properties = TestProperties.getInstance().getProperties();

    @BeforeClass
    public static void setUp() throws Exception{
        System.setProperty("webdriver.chrome.driver",properties.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();

        baseUrl = properties.getProperty("app.url");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterClass
    public static void tearDown() throws Exception{
        driver.quit();
    }

    @Test
    public void DNSShop() throws InterruptedException{
        driver.navigate().to(properties.getProperty("app.url"));
        WebDriverWait wait = new WebDriverWait(driver,30);

        DNSPage dnsPage = new DNSPage(driver);
        SearchPage searchPage = new SearchPage(driver);
        PSPage psPage = new PSPage(driver);
        DetroitPage detroitPage = new DetroitPage(driver);
        CartPage cartPage = new CartPage(driver);

        dnsPage.inputSearchPS();
        searchPage.goToPSPage();
        int priceForPs = psPage.getPriceCurrent();
        psPage.selectWarranty2Years();
        int priceForPsWithWarranty = psPage.getPriceCurrent();
        Assert.assertEquals("Ошибка цена PS не совпадает",priceForPs,25999);
        Assert.assertEquals("Ошибка цена Detroit не совпадает",priceForPsWithWarranty,30679);
        psPage.addToCart();
        dnsPage.inputSearchDetroit();
        int priceForDetroit = detroitPage.getPriceCurrent();
        detroitPage.addToCart();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(detroitPage.CartPrice));
        Assert.assertEquals("Ошибка цена корзины не совпадает с суммой покупок",
                priceForPsWithWarranty + priceForDetroit,detroitPage.getCartPrice());
        detroitPage.goToCart();
        Thread.sleep(5000);
        String compare = "Продленная гарантия на 24 мес. ";
        Thread.sleep(5000);
        Assert.assertTrue("Ошибка, гарантия не совпадает", cartPage.warrantyCheck().contains(compare));
        int priceForPsWithWarrantyWithDetroit = cartPage.getCartTotalPrice();
        Assert.assertEquals("Ошибка цена PS не совпадает",priceForPs,cartPage.getCartProduct1Price());
        Assert.assertEquals("Ошибка цена Detroit не совпадает",priceForDetroit,cartPage.getCartProduct2Price());
        Assert.assertEquals("Ошибка цена корзины не совпадает",
                priceForPsWithWarranty + priceForDetroit, priceForPsWithWarrantyWithDetroit);
        cartPage.delete2Product();
        Assert.assertEquals("Ошибка, в корзине есть Detroit",cartPage.getAmountOfCartProducts(),1);
        Thread.sleep(5000);
        Assert.assertEquals("Ошибка, сумма корзины не уменьшилась",
                detroitPage.getCartPrice(), priceForPsWithWarrantyWithDetroit - priceForDetroit);

    }
}
