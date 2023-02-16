package assertion;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static util.ObjectMapperConversion.JSONToObject;

public abstract class ModelToObject {

    public static <T> T getResponseBody(HttpResponse response, Class clazz) throws IOException {
        String body = EntityUtils.toString(response.getEntity());
        return JSONToObject(body, clazz);
    }
}
