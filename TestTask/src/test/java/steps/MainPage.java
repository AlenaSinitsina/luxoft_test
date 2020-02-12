package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.lang.Thread.sleep;


public class MainPage {

    WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//div[text()='Список дел']")
    private WebElement toDoList;

    @FindBy(xpath = "//span[@class='error' and text()='Неверные логин или пароль']")
    private WebElement authorizationError;

    @FindBy(xpath = "//button[text()='Выход']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//div[@class='create-form']//input")
    private WebElement controlField;

    @FindBy(xpath = "//div[@class='create-form']  //button[@class='button']")
    private WebElement controlButton;

    @FindAll({
            @FindBy(xpath = "//div[@class='view-container']  //div[@class='single-todo'] //div[@class='todo-description']")})
    private List<WebElement> todolist;

    @FindAll({
            @FindBy(xpath = "//div[@class='view-container']  //div[@class='single-todo'] //button[@class='icon-button red-icon']")})
    private List<WebElement> todolistButton;


    public Boolean checkToDoListOnPage(){
        return toDoList.isDisplayed();
    }

    public Boolean checkAuthorizationErrorOnPage() {
        return authorizationError.isDisplayed();
    }

    public void clickButtonSignOut(){
        buttonSignOut.click();
    }

    public void setControlField(String text){
        controlField.sendKeys(text);
        controlButton.click();
    }

    public String getLine(int numberLine){
        return todolist.get(numberLine).getAttribute("innerText");
    }

    public int getCountLine(){
        return todolist.size();
    }

    public void deleteAllTodoList() throws InterruptedException {
        for (WebElement i : todolistButton) {
            todolistButton.get(0).click();
            sleep(1000);
        }
    }
}
