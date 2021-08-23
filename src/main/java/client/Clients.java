package client;

import lombok.extern.java.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;

import static config.Endpoints.*;

@Log
public class Clients extends Base {

    public static HttpResponse getAllUsers(String value, String token) throws Exception {
        log.info("GET USER REQUEST AGAINST: " + GET_USERS + " endpoint");
        URI uri = new URIBuilder(GET_USERS)
                .addParameter("page", value)
                .build();
        return httpGet(uri.toString(), token);

    }

    public static HttpResponse getUser(String userId, String token) throws Exception {
        log.info("GET USER REQUEST AGAINST: " + GET_USER + " endpoint");
        URI uri = new URIBuilder(GET_USER + userId)
                .build();
        return httpGet(uri.toString(), token);
    }

    public static HttpResponse createUser(String payload) throws Exception {
        log.info("CREATE USER REQUEST AGAINST " + CREATE_USER + " endpoint with payload: " + payload);
        URI uri = new URIBuilder(CREATE_USER)
                .build();
        return httpPost(uri.toString(), payload);
    }

    public static HttpResponse updateUser(int employee, String payload, String token) throws Exception {
        log.info("UPDATE USER REQUEST AGAINST: " + UPDATE_USER + employee + " endpoint with payload: " + payload);
        URI uri = new URIBuilder(UPDATE_USER + employee)
                .build();
        return httpPut(uri.toString(), payload, token);
    }

    public static HttpResponse deleteUser(int employee, String token) throws Exception {
        log.info("DELETE USER REQUEST AGAINST: " + DELETE_USER + employee + " endpoint");
        URI uri = new URIBuilder(DELETE_USER + employee)
                .build();
        return httpDelete(uri.toString(), token);
    }

    public static HttpResponse loginUser(String payload) throws Exception {
        log.info("LOGIN REQUEST AGAINST: " + LOGIN + " endpoint");
        URI uri = new URIBuilder(LOGIN)
                .build();
        return httpPost(uri.toString(), payload);
    }

    public static HttpResponse register(String payload) throws Exception {
        log.info("REGISTER REQUEST AGAINST " + REGISTER + " endpoint with payload: " + payload);
        URI uri = new URIBuilder(REGISTER)
                .build();
        return httpPost(uri.toString(), payload);
    }
}
