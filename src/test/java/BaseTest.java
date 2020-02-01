import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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
       // Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);

        DNSPage dnsPage = new DNSPage(driver);
        SearchPage searchPage = new SearchPage(driver);
        PSPage psPage = new PSPage(driver);
        DetroitPage detroitPage = new DetroitPage(driver);
        CartPage cartPage = new CartPage(driver);
        Product product = new Product();

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
        wait.until(ExpectedConditions.elementToBeClickable(detroitPage.CartButton));
        Assert.assertEquals("Ошибка цена корзины не совпадает с суммой покупок",
                priceForPsWithWarranty + priceForDetroit,detroitPage.getCartPrice());
        detroitPage.goToCart();
        wait.until(ExpectedConditions.visibilityOf(cartPage.deleteTry().get(0)));
        String compare = "Продленная гарантия на 24 мес. ";
        Assert.assertTrue("Ошибка, гарантия не совпадает", cartPage.warrantyCheck().contains(compare));
        int priceForPsWithWarrantyWithDetroit = cartPage.getCartTotalPrice(cartPage.cartTry());
        Assert.assertEquals("Ошибка цена PS не совпадает",priceForPs,cartPage.getCartProduct1Price(cartPage.cartTry()));
        Assert.assertEquals("Ошибка цена Detroit не совпадает",priceForDetroit,cartPage.getCartProduct2Price(cartPage.cartTry()));
        Assert.assertEquals("Ошибка цена корзины не совпадает",
                priceForPsWithWarranty + priceForDetroit, priceForPsWithWarrantyWithDetroit);
        cartPage.delete2Product(cartPage.deleteTry());
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.removedDeleteButton));
        Assert.assertEquals("Ошибка, в корзине есть Detroit",cartPage.getAmountOfCartProducts(),1);
        Assert.assertEquals("Ошибка, сумма корзины не уменьшилась",
                cartPage.getCartTotalPrice(cartPage.cartTry()), priceForPsWithWarrantyWithDetroit - priceForDetroit);
        cartPage.addAmountOfProduct();
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.addAmountOfProductButton));
        //wait.until(ExpectedConditions.attributeToBe(cartPage.amountOfCartProducts,"text","2"));
        Thread.sleep(2000);
        cartPage.addAmountOfProduct();
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.addAmountOfProductButton));
        //wait.until(ExpectedConditions.attributeToBe(cartPage.amountOfCartProducts,"text","3"));
        Thread.sleep(2000);
        Assert.assertEquals("В корзине нет 3х продуктов",cartPage.getAmountOfCartProducts(),3);
        Assert.assertEquals("Сумма корзины не равна 3м PS",
                cartPage.getCartTotalPrice(cartPage.cartTry()),priceForPsWithWarranty*3);
        cartPage.removedDeletedProduct();
        Thread.sleep(2000);
        Assert.assertEquals("В корзине Detroit продуктов",cartPage.getAmountOfCartProducts(),4);
    }
}
