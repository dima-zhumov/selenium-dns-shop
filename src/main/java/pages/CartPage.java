package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CartPage extends BasePage {
    WebDriver driver;

    public CartPage (WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy (xpath = "//div[contains(@class,'list')]/span[text()]")
    public WebElement warrantyButton;


    public List<WebElement> cartTry(){
        return this.driver.findElements(By.xpath("//span[@class='price__current']"));
    }

    public List<WebElement> deleteTry(){
        return this.driver.findElements(By.xpath("//button[text()='Удалить']"));
    }

    @FindBy (xpath = "//span[text()='Основные товары ']/span")
    public WebElement amountOfCartProducts;

    @FindBy (xpath = "//button[contains(@class,'plus')]")
    public WebElement addAmountOfProductButton;

    @FindBy (xpath = "//span[contains(@class,'price') and contains(@class,'applied')]")
    public WebElement priceOfWarranty;

    @FindBy (xpath = "//span[@class='restore-last-removed']")
    public WebElement removedDeleteButton;

    public String warrantyCheck(){
        return warrantyButton.getText();
    }

    public int getCartProduct1Price(List<WebElement> cart){
        return Integer.parseInt(cart.get(0).getText().replaceAll("[^\\d]",""));
    }

    public int getCartProduct2Price(List<WebElement> cart){
        return Integer.parseInt(cart.get(1).getText().replaceAll("[^\\d]",""));
    }

    public int getCartTotalPrice(List<WebElement> cart){
        return Integer.parseInt(cart.get(cart.size()-1).getText().replaceAll("[^\\d]",""));
    }


    public void delete2Product(List<WebElement> delete){
        delete.get(1).click();
    }

    public int getAmountOfCartProducts(){
        return Integer.parseInt(amountOfCartProducts.getText());
    }

    public void addAmountOfProduct(){
        addAmountOfProductButton.click();
    }

    public String getPriceOfWarrantyString(){
       return priceOfWarranty.getText();
    }

    public void removedDeletedProduct(){
        removedDeleteButton.click();
    }




}
