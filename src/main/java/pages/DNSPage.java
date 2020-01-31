package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DNSPage extends BasePage{
    WebDriver driver;

    public DNSPage (WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy (xpath = "//div[@class='container']//input[@type='search']")
    public WebElement search;

    public void inputSearchPS(){
        search.sendKeys("playstation");
        search.sendKeys(Keys.ENTER);
    }

    public void inputSearchDetroit(){
        search.sendKeys("Detroit");
        search.sendKeys(Keys.ENTER);
    }


}
