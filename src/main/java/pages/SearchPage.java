package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage{
    WebDriver driver;

    public SearchPage (WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy (xpath = "//div[contains(@class,'title-link')]//a[contains(@href,'product/e62623677df11b80')]")
    public WebElement userSelectedPlaystation;

    public void goToPSPage(){
        userSelectedPlaystation.click();
    }

}
