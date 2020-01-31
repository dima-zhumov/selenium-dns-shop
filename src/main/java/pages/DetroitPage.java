package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DetroitPage extends BasePage{
    WebDriver driver;

    public DetroitPage (WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@class='current-price-value']")
    public WebElement priceCurrent;

    @FindBy (xpath = "//div[contains(@class,'wrapper')]//button[@data-id='cart-button']")
    public WebElement CartButton;

    @FindBy (xpath = "//span[@class='cart-link__price']")
    public WebElement CartPrice;

    public int getPriceCurrent(){
       return Integer.parseInt(priceCurrent.getText().replaceAll("[^\\d]",""));
    }

    public void addToCart(){
        CartButton.click();
    }

    public int getCartPrice(){
       return Integer.parseInt(CartPrice.getText().replaceAll("[^\\d]",""));
    }

    public void goToCart(){
        CartPrice.click();
    }
}
