package steps;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;


public class Rest_Steps {

    public String result;
    private JSONObject jsonREST;

    public static com.mashape.unirest.http.HttpResponse<JsonNode> sendPostRequest(String url, String body) throws UnirestException {
        return Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(body)
                .asJson();
    }

    @Given("Send post on Authorization with login {string} and password {string}")
    public void sendPostOnAuthorization(String login, String password) throws UnirestException {
        String body = "{" +
                "\"password\": \"" + password +
                "\",  \"username\": \"" + login +
                "\"}";
        result= String.valueOf(sendPostRequest("http://localhost:7844/api/login",body).getBody());
    }

    @Then("Status rest {string}")
    public void authorizationResult(String res) {
        Assert.assertEquals(result, "{\"status\":\""+res+"\"}");
    }

    @When("Send post for logout")
    public void sendPostForLogout() throws UnirestException {
        com.mashape.unirest.http.HttpResponse<JsonNode> jsonResponse = sendPostRequest("http://localhost:7844/api/logout","");
        result= String.valueOf(jsonResponse.getStatus());
    }

    @When("Add string {string} use rest")
    public void addStringUseRest(String value) throws UnirestException {
        String body =  "{ \"description\": \"" + value + "\"}";
        com.mashape.unirest.http.HttpResponse<JsonNode> jsonResponse = sendPostRequest("http://localhost:7844/api/create", body);
        jsonREST= (JSONObject) jsonResponse.getBody().getObject().get("todo");
        result = jsonREST.toString();
    }

    @Then("Line {string} has been add")
    public void lineHasBeen(String value) {
        Assert.assertTrue(result.contains("{\"description\":\"" + value + "\""));
    }

    @When("Delete line use rest")
    public void deleteLineUseRest() throws UnirestException {
        int numLine = (int) jsonREST.get("id");
        String body =  "{ \"id\": \"" + numLine + "\"}";
        com.mashape.unirest.http.HttpResponse<JsonNode> jsonResponse = sendPostRequest("http://localhost:7844/api/remove", body);
        result = String.valueOf(jsonResponse.getBody());
    }
}
