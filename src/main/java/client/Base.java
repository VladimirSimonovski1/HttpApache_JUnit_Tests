package client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Base {
    HttpClient httpClient = HttpClients.custom().build();

    protected HttpResponse httpGet(String endpoint) throws Exception {
        HttpGet request = new HttpGet(endpoint);
        HttpResponse response = httpClient.execute(request);
        getResponseEntity(response);
        return response;
    }

    protected HttpResponse httpPost(String endpoint, String PAYLOAD) throws IOException {
        HttpPost request = new HttpPost(endpoint);
        request.setEntity(new StringEntity(PAYLOAD));
        HttpResponse response = httpClient.execute(request);
        getResponseEntity(response);
        return response;
    }

    protected HttpResponse httpPut(String endpoint, String jsonPayload) throws IOException {
        HttpPut request = new HttpPut(endpoint);
        request.setEntity(new StringEntity(jsonPayload));
        HttpResponse response = httpClient.execute(request);
        getResponseEntity(response);
        return response;
    }

    protected HttpResponse httpDelete(String endpoint) throws IOException {
        HttpDelete request = new HttpDelete(endpoint);
        HttpResponse response = httpClient.execute(request);
        getResponseEntity(response);
        return response;
    }

    private void getResponseEntity(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(response.getEntity());
        HttpEntity newEntity = new StringEntity(body, ContentType.get(entity));
        response.setEntity(newEntity);
    }
}
