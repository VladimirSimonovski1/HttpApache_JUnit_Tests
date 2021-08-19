package client;

import lombok.extern.java.Log;
import org.apache.http.HttpResponse;

import static config.Endpoints.*;

@Log
public class Clients extends Base {

    public static HttpResponse getAllUsers() throws Exception {
        log.info("GET USER REQUEST AGAINST: " + GET_USERS + " endpoint");
        return httpGet(GET_USERS);
    }

    public static HttpResponse getUser(String userId) throws Exception {
        log.info("GET USER REQUEST AGAINST: " + GET_USER + " endpoint");
        return httpGet(GET_USER + userId);
    }

    public static HttpResponse createUser(String payload) throws Exception {
        log.info("CREATE USER REQUEST AGAINST " + CREATE_USER + " endpoint with payload: " + payload);
        return httpPost(CREATE_USER, payload);
    }

    public static HttpResponse updateUser(int employee, String payload) throws Exception {
        log.info("UPDATE USER REQUEST AGAINST: " + UPDATE_USER + employee + " endpoint with payload: " + payload);
        return httpPut(UPDATE_USER + employee, payload);
    }

    public static HttpResponse deleteUser(int employee) throws Exception {
        log.info("DELETE USER REQUEST AGAINST: " + DELETE_USER + employee + " endpoint");
        return httpDelete(DELETE_USER + employee);
    }
}
