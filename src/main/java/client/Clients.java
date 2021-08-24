package client;

import lombok.extern.java.Log;
import org.apache.http.HttpResponse;

import static config.Endpoints.CREATE_USER;
import static config.Endpoints.DELETE_USER;
import static config.Endpoints.GET_USER;
import static config.Endpoints.GET_USERS;
import static config.Endpoints.LOGIN;
import static config.Endpoints.REGISTER;
import static config.Endpoints.UPDATE_USER;

@Log
public class Clients extends Base {

    public static HttpResponse getAllUsers(String value, String token) throws Exception {
        log.info("GET USER REQUEST AGAINST: " + GET_USERS + " endpoint");
        String uri = endpointBuilder(GET_USERS)
                .addParameter("page", value)
                .build().toString();
        return httpGet(uri, token);
    }

    public static HttpResponse getUser(String userId, String token) throws Exception {
        log.info("GET USER REQUEST AGAINST: " + GET_USER + " endpoint");
        String uri = endpointBuilder(GET_USER + userId)
                .build().toString();
        return httpGet(uri, token);
    }

    public static HttpResponse createUser(String payload) throws Exception {
        log.info("CREATE USER REQUEST AGAINST " + CREATE_USER + " endpoint with payload: " + payload);
        String uri = endpointBuilder(CREATE_USER)
                .build().toString();
        return httpPost(uri, payload);
    }

    public static HttpResponse updateUser(int employee, String payload, String token) throws Exception {
        log.info("UPDATE USER REQUEST AGAINST: " + UPDATE_USER + employee + " endpoint with payload: " + payload);
        String uri = endpointBuilder(UPDATE_USER)
                .build().toString();
        return httpPut(uri, payload, token);
    }

    public static HttpResponse deleteUser(int employee, String token) throws Exception {
        log.info("DELETE USER REQUEST AGAINST: " + DELETE_USER + employee + " endpoint");
        String uri = endpointBuilder(DELETE_USER)
                .build().toString();
        return httpDelete(uri, token);
    }

    public static HttpResponse loginUser(String payload) throws Exception {
        log.info("LOGIN REQUEST AGAINST: " + LOGIN + " endpoint");
        String uri = endpointBuilder(LOGIN)
                .build().toString();
        return httpPost(uri, payload);
    }

    public static HttpResponse register(String payload) throws Exception {
        log.info("REGISTER REQUEST AGAINST " + REGISTER + " endpoint with payload: " + payload);
        String uri = endpointBuilder(REGISTER)
                .build().toString();
        return httpPost(uri, payload);
    }
}
