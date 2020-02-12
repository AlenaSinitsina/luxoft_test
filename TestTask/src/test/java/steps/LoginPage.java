package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

   WebDriver webDriver;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//input[@name='username']")
    private WebElement authorizationName;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement authorizationPassword;

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement authorizationButton;

    public void setAuthorizationName(String name){
        authorizationName.click();
        authorizationName.sendKeys(name);
    }

    public void setAuthorizationPassword(String password){
        authorizationPassword.sendKeys(password);
    }

    public void clickAuthorizationButton(){
        authorizationButton.click();
    }

    public Boolean checkAuthorizationButton() {
       return authorizationButton.isDisplayed();
    }
}
