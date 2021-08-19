import lombok.extern.java.Log;
import org.apache.http.HttpResponse;
import org.junit.Test;

import static assertion.TestAssertions.*;
import static client.Clients.*;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static payload.UserRequestPayload.userRequestBody;
import static util.ObjectMapperConversion.objectToString;

@Log
public class ApacheJUnitTests {

    @Test
    public void shouldGetAllUsers() throws Exception {
        HttpResponse actualResponse = getAllUsers();
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertSuccessfulGetUsersResponse(actualResponse);
    }

    @Test
    public void shouldGetUser() throws Exception {
        HttpResponse actualResponse = getUser("1");
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

        HttpResponse actualResponse = updateUser(2, updateUserRequestBody);
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertSuccessfulUpdateUserResponse(actualResponse);
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        HttpResponse actualResponse = deleteUser(2);
        log.info("ACTUAL RESPONSE: " + actualResponse);

        assertThat(actualResponse.getStatusLine().getStatusCode(), is(equalTo(SC_NO_CONTENT)));
    }
}
