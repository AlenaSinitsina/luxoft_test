package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import web.Chromer;


public class UI_Steps {

    private MainPage mainPage = Chromer.mainPage;
    private LoginPage loginPage = Chromer.loginPage;

    @When("Fill in login {string} and password {string}")
    public void fillInLoginAndPassword(String login, String password) {
        loginPage.setAuthorizationName(login);
        loginPage.setAuthorizationPassword(password);
        loginPage.clickAuthorizationButton();
    }

    @Then("^Login (successful|failed)$")
    public void pageIsOpen(String result) throws InterruptedException {
        if (result.equals("successful")) {
            Assert.assertTrue(mainPage.checkToDoListOnPage());
        } else
            Assert.assertTrue(mainPage.checkAuthorizationErrorOnPage());
    }

    @When("Click on button Sign out")
    public void clickOnButtonSignOut() {
        mainPage.clickButtonSignOut();
    }

    @Then("Authorization form is open")
    public void authorizationFormIsOpen() {
        Assert.assertTrue(loginPage.checkAuthorizationButton());
    }

    @When("Add string {string}")
    public void addString(String text) {
        mainPage.setControlField(text);
    }

    @Then("In line {int} contains {string}")
    public void inLineContains(int numberLine, String text) {
        Assert.assertEquals(mainPage.getLine(numberLine - 1), text);
    }

    @And("Delete all line")
    public void deleteAllLine() throws InterruptedException {
        mainPage.deleteAllTodoList();
    }

    @Then("To-do list contains {int} line")
    public void toDoListContainsLine(int countLine) {
        Assert.assertEquals(mainPage.getCountLine(), countLine);
    }
}
