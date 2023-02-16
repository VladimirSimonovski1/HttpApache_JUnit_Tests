import com.fasterxml.jackson.core.JsonProcessingException;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import lombok.extern.java.Log;
import model.UsersResponseModel;
import model.submodel.UserDataModel;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.UserDataProvider;

import java.util.List;
import java.util.stream.Collectors;

import static assertion.ModelToObject.getResponseBody;
import static assertion.TestAssertions.assertFailedRegistrationResponse;
import static assertion.TestAssertions.assertSuccessfulCreateUserResponse;
import static assertion.TestAssertions.assertSuccessfulCreateUsersResponse;
import static assertion.TestAssertions.assertSuccessfulGetUserResponse;
import static assertion.TestAssertions.assertSuccessfulGetUsersPerIdResponse;
import static assertion.TestAssertions.assertSuccessfulGetUsersResponse;
import static assertion.TestAssertions.assertSuccessfulUpdateUserResponse;
import static assertion.TestAssertions.assertUsersLength;
import static client.Clients.createUser;
import static client.Clients.deleteUser;
import static client.Clients.getAllUsers;
import static client.Clients.getUser;
import static client.Clients.loginUser;
import static client.Clients.register;
import static client.Clients.updateUser;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static payload.RegisterLoginRequestPayload.registerLoginRequestBody;
import static payload.UserRequestPayload.userRequestBody;
import static util.ObjectMapperConversion.objectToString;

@Log @RunWith(DataProviderRunner.class)
public class ApacheJUnitTests {

    private static String loginToken;

    @BeforeClass
    public static void login() throws Exception {
        HttpResponse loginResponse = loginUser(registerLoginRequestBody());
        log.info("ACTUAL RESPONSE: " + loginResponse);

        String loginBodyToString = EntityUtils.toString(loginResponse.getEntity());
        JSONObject loginBody = new JSONObject(loginBodyToString);
        log.info("JSON OBJECT: " + loginBody);

        loginToken = loginBody.get("token").toString();
        log.info("LOGIN SUCCESSFUL, TOKEN " + loginToken + " IS FETCHED!");
    }

    @AfterClass
    public static void reportGenerated() {
        log.info("AN .XML REPORT SUCCESSFULLY GENERATED IN target/surefire-reports!");
        log.info("RUN allure serve target/surefire-reports TO GENERATE AN .HTML REPORT!");
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        HttpResponse actualResponse = getAllUsers("1", loginToken);
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertSuccessfulGetUsersResponse(actualResponse);
    }

    @Test
    public void shouldGetUser() throws Exception {
        HttpResponse actualResponse = getUser("1", loginToken);
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertSuccessfulGetUserResponse(actualResponse);
    }

    @Test
    public void shouldCreateUser() throws Exception {
        log.info("REQUEST BODY: " + userRequestBody());

        HttpResponse actualResponse = createUser(userRequestBody());
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertSuccessfulCreateUserResponse(actualResponse);
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        log.info("REQUEST BODY: " + userRequestBody());

        HttpResponse actualResponse = updateUser(2, userRequestBody(), loginToken);
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertSuccessfulUpdateUserResponse(actualResponse);
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        HttpResponse actualResponse = deleteUser(2, loginToken);
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertThat(actualResponse.getStatusLine().getStatusCode(), is(equalTo(SC_NO_CONTENT)));
    }

    @Test
    public void countTheNumberOfUsersOnSecondPage() throws Exception {
        HttpResponse actualResponse = getAllUsers("2", loginToken);
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertUsersLength(actualResponse);
    }

    @Test @UseDataProvider(value = "getUserPerIdDataProvider", location = UserDataProvider.class)
    public void shouldGetUsersPerId(String userId, String email, String firstName, String lastName, String avatar) throws Exception {
        HttpResponse actualResponse = getUser(userId, loginToken);
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertSuccessfulGetUsersPerIdResponse(actualResponse, email, firstName, lastName, avatar);
    }

    @Test @UseDataProvider(value = "createUsersDataProvider", location = UserDataProvider.class)
    public void shouldCreateUsers(String name, String job) throws Exception {
        HttpResponse actualResponse = createUser(userRequestBody(name, job));
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertSuccessfulCreateUsersResponse(actualResponse, name, job);
    }

    @Test @UseDataProvider(value = "createRegisterDataProvider", location = UserDataProvider.class)
    public void shouldFailRegistering(String email, String password, String error) throws Exception {
        HttpResponse actualResponse = register(registerLoginRequestBody(email, password));
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertFailedRegistrationResponse(actualResponse, error);
    }

    @Test
    public void shouldGetAllUsersUsingStream() throws Exception {
        HttpResponse actualResponse = getAllUsers("1", loginToken);
        log.info("ACTUAL RESPONSE: " + actualResponse);
        UsersResponseModel userResponseBody = getResponseBody(actualResponse, UsersResponseModel.class);

        userResponseBody.getData().stream().map(item -> {
            if(item.getId() == 3) {
                try {
                    log.info("STREAMED LIST: " + objectToString(item));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return item;
            } else {
                return null;
            }
        });
        List<UserDataModel> udm1 =
                userResponseBody.getData().stream().filter(item -> item.getFirst_name().startsWith("T")).collect(Collectors.toList());
        log.info("FILTERED LIST: " + objectToString(udm1));
        userResponseBody.getData().forEach(item -> {
            if(item.getAvatar().contains("2-image")) {
                try {
                    log.info("JANET: " + objectToString(item));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
