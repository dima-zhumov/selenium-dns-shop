package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PSPage extends BasePage{
    WebDriver driver;
    public PSPage (WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy (xpath = "//span[@class='current-price-value']")
    public WebElement priceCurrent;

    @FindBy (xpath = "//select[@class='form-control select']")
    public WebElement Warranty;

    @FindBy (xpath = "//option[@value='2']")
    public WebElement warranty2Years;

    @FindBy (xpath = "//div[contains(@class,'wrapper')]//button[@data-id='cart-button']")
    public WebElement CartButton;

    public int getPriceCurrent(){
        return Integer.parseInt(priceCurrent.getText().replaceAll("[^\\d]",""));
    }

    public void selectWarranty2Years(){
        Warranty.click();
        warranty2Years.click();
    }

    public void addToCart(){
        CartButton.click();
    }



}
