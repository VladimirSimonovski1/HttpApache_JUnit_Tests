import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import lombok.extern.java.Log;
import model.RegisterLoginRequestModel;
import model.UserRequestModel;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.UserDataProvider;

import static assertion.TestAssertions.*;
import static client.Clients.*;
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
        String createRegisterRequestBody = objectToString(registerLoginRequestBody);
        HttpResponse loginResponse = loginUser(createRegisterRequestBody);
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
        String createUserRequestBody = objectToString(userRequestBody);
        log.info("REQUEST BODY: " + createUserRequestBody);

        HttpResponse actualResponse = createUser(createUserRequestBody);
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertSuccessfulCreateUserResponse(actualResponse);
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        String updateUserRequestBody = objectToString(userRequestBody);
        log.info("REQUEST BODY: " + updateUserRequestBody);

        HttpResponse actualResponse = updateUser(2, updateUserRequestBody, loginToken);
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
        UserRequestModel userRequestBody = UserRequestModel.builder()
                .name(name)
                .job(job)
                .build();
        String createUserRequestBody = objectToString(userRequestBody);

        HttpResponse actualResponse = createUser(createUserRequestBody);
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertSuccessfulCreateUsersResponse(actualResponse, name, job);
    }

    @Test @UseDataProvider(value = "createRegisterDataProvider", location = UserDataProvider.class)
    public void shouldFailRegistering(String email, String password, String error) throws Exception {
        RegisterLoginRequestModel registerLoginRequestBody = RegisterLoginRequestModel.builder()
                .email(email)
                .password(password)
                .build();
        String createRegisterRequestBody = objectToString(registerLoginRequestBody);

        HttpResponse actualResponse = register(createRegisterRequestBody);
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertFailedRegistrationResponse(actualResponse, error);
    }
}
