package web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.LoginPage;
import steps.MainPage;


public class Chromer {
    public static WebDriver driver;
    public static WebDriverWait waitVar;
    public static MainPage mainPage;
    public static LoginPage loginPage;

    public static WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        driver.manage().window().maximize();
        waitVar = new WebDriverWait(driver, 10);
        options.setHeadless(false);
        driver.get("http://localhost:7844");
        return driver;
    }

    public static void initElements(){
        mainPage = new MainPage(driver);
        loginPage =new LoginPage(driver);
    }

    public static void closeDriver() {
        driver.close();
    }
}
