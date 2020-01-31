package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartPage extends BasePage {
    WebDriver driver;

    public CartPage (WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy (xpath = "//div[contains(@class,'list')]/span[text()]")
    public WebElement warrantyButton;

    @FindBy ( xpath = "//span[@class='price__current'][1]")
    public WebElement cartProduct1Price;

    @FindBy ( xpath = "//span[@class='price__current'][2]")
    public WebElement cartProduct2Price;

    @FindBy ( xpath = "//span[@class='price__current'][3]")
    public WebElement cartTotalPrice;


    @FindBy (xpath = "//button[text()='Удалить'][2]")
    public WebElement delete2ProductButton;

    @FindBy (xpath = "//span[text()='Основные товары ']/span")
    public WebElement amountOfCartProducts;

    @FindBy (xpath = "//button[contains(@class,'plus')]")
    public WebElement addAmountOfProductButton;

    @FindBy (xpath = "//span[contains(@class,'price') and contains(@class,'applied')]")
    public WebElement priceOfWarranty;

    @FindBy (xpath = "//span[contains(text(),'Вернуть')]")
    public WebElement returnDeletedProductButton;

    public String warrantyCheck(){
        return warrantyButton.getText();
    }

    public int getCartProduct1Price(){
        return Integer.parseInt(cartProduct1Price.getText().replaceAll("[^\\d]",""));
    }

    public int getCartProduct2Price(){
        return Integer.parseInt(cartProduct2Price.getText().replaceAll("[^\\d]",""));
    }

    public int getCartTotalPrice(){
        return Integer.parseInt(cartTotalPrice.getText().replaceAll("[^\\d]",""));
    }




    public void delete2Product(){
        delete2ProductButton.click();
    }

    public int getAmountOfCartProducts(){
        return Integer.parseInt(amountOfCartProducts.getText());
    }

    public void AddAmountOfProduct(){
        addAmountOfProductButton.click();
    }

    public String getPriceOfWarrantyString(){
       return priceOfWarranty.getText();
    }

    public void returnDeletedProductButton(){
        returnDeletedProductButton.click();
    }




}
