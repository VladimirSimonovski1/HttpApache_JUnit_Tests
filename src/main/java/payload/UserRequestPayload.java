package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.UserRequestModel;

import static util.ObjectMapperConversion.objectToString;

public class UserRequestPayload {

    public static String userRequestBody() throws JsonProcessingException {
        UserRequestModel userRequestBody = UserRequestModel.builder()
                .name("Vladimir")
                .job("QA")
                .build();
        return objectToString(userRequestBody);
    }

    public static String userRequestBody(String name, String job) throws JsonProcessingException {
        UserRequestModel userRequestBody = UserRequestModel.builder()
                .name(name)
                .job(job)
                .build();
        return objectToString(userRequestBody);
    }
}
