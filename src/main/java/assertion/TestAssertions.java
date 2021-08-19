package assertion;

import lombok.extern.java.Log;
import model.*;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static util.ObjectMapperConversion.JSONToObject;
import static util.ObjectMapperConversion.objectToString;

@Log
public class TestAssertions {

    public static void assertSuccessfulGetUsersResponse(HttpResponse response) throws IOException {
        UsersResponseModel userResponseBody = getUsersResponseBody(response);
        log.info("RESPONSE BODY: " + objectToString(userResponseBody));

        assertThat(response.getStatusLine().getStatusCode(), equalTo(SC_OK));
        assertThat(userResponseBody, allOf(
                hasProperty("page", is(equalTo(1))),
                hasProperty("per_page", is(equalTo(6))),
                hasProperty("total", is(equalTo(12))),
                hasProperty("total_pages", is(equalTo(2))),
                hasProperty("data", hasItem(allOf(
                                hasProperty("id", is(equalTo(1))),
                                hasProperty("email", is(equalTo("george.bluth@reqres.in"))),
                                hasProperty("first_name", is(equalTo("George"))),
                                hasProperty("last_name", is(equalTo("Bluth"))),
                                hasProperty("avatar", is(equalTo("https://reqres.in/img/faces/1-image.jpg")))
                        )
                )),
                hasProperty("support", allOf(
                        hasProperty("url", is(equalTo("https://reqres.in/#support-heading"))),
                        hasProperty("text", is(equalTo("To keep ReqRes free, contributions towards server costs are appreciated!")))
                ))
        ));
    }

    public static void assertSuccessfulGetUserResponse(HttpResponse response) throws IOException {
        UserResponseModel userResponseBody = getUserResponseBody(response);
        log.info("RESPONSE BODY: " + objectToString(userResponseBody));

        assertThat(response.getStatusLine().getStatusCode(), equalTo(SC_OK));
        assertThat(userResponseBody, allOf(
                hasProperty("data", allOf(
                                hasProperty("id", is(equalTo(1))),
                                hasProperty("email", is(equalTo("george.bluth@reqres.in"))),
                                hasProperty("first_name", is(equalTo("George"))),
                                hasProperty("last_name", is(equalTo("Bluth"))),
                                hasProperty("avatar", is(equalTo("https://reqres.in/img/faces/1-image.jpg")))
                        )
                ),
                hasProperty("support", allOf(
                        hasProperty("url", is(equalTo("https://reqres.in/#support-heading"))),
                        hasProperty("text", is(equalTo("To keep ReqRes free, contributions towards server costs are appreciated!")))
                ))
        ));
    }

    public static void assertSuccessfulCreateUserResponse(HttpResponse response) throws IOException {
        UserResponseAfterRequestModel userResponseAfterRequestBody = userResponseAfterRequestModel(response);
        log.info("RESPONSE BODY: " + objectToString(userResponseAfterRequestBody));

        assertThat(response.getStatusLine().getStatusCode(), equalTo(SC_CREATED));
        assertThat(userResponseAfterRequestBody, allOf(
                hasProperty("name", is(equalTo("Vladimir"))),
                hasProperty("job", is(equalTo("QA"))),
                hasProperty("id", is(notNullValue())),
                hasProperty("createdAt", is(notNullValue()))
        ));
    }

    public static void assertSuccessfulUpdateUserResponse(HttpResponse response) throws IOException {
        UserResponseAfterRequestModel userResponseAfterRequestBody = userResponseAfterRequestModel(response);
        log.info("RESPONSE BODY: " + objectToString(userResponseAfterRequestBody));

        assertThat(response.getStatusLine().getStatusCode(), equalTo(SC_OK));
        assertThat(userResponseAfterRequestBody, allOf(
                hasProperty("name", is(equalTo("Vladimir"))),
                hasProperty("job", is(equalTo("QA"))),
                hasProperty("updatedAt", is(notNullValue()))
        ));
    }

    private static UsersResponseModel getUsersResponseBody(HttpResponse response) throws IOException {
        UsersResponseModel usersResponseBody;
        String body = EntityUtils.toString(response.getEntity());
        usersResponseBody = JSONToObject(body, UsersResponseModel.class);

        return usersResponseBody;
    }

    private static UserResponseModel getUserResponseBody(HttpResponse response) throws IOException {
        UserResponseModel userResponseBody;
        String body = EntityUtils.toString(response.getEntity());
        userResponseBody = JSONToObject(body, UserResponseModel.class);

        return userResponseBody;
    }

    private static UserResponseAfterRequestModel userResponseAfterRequestModel(HttpResponse response) throws IOException {
        UserResponseAfterRequestModel userResponseAfterRequestModel;
        String body = EntityUtils.toString(response.getEntity());
        userResponseAfterRequestModel = JSONToObject(body, UserResponseAfterRequestModel.class);

        return userResponseAfterRequestModel;
    }
}
