package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.RegisterLoginRequestModel;

import static util.ObjectMapperConversion.objectToString;

public class RegisterLoginRequestPayload {

    public static String registerLoginRequestBody() throws JsonProcessingException {
        RegisterLoginRequestModel registerLoginRequestBody = RegisterLoginRequestModel.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        return objectToString(registerLoginRequestBody);
    }

    public static String registerLoginRequestBody(String email, String password) throws JsonProcessingException {
        RegisterLoginRequestModel registerLoginRequestBody = RegisterLoginRequestModel.builder()
                .email(email)
                .password(password)
                .build();
        return objectToString(registerLoginRequestBody);
    }
}
