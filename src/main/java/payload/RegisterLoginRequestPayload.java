package payload;

import model.RegisterLoginRequestModel;

public class RegisterLoginRequestPayload {

    public static RegisterLoginRequestModel registerLoginRequestBody = RegisterLoginRequestModel.builder()
            .email("eve.holt@reqres.in")
            .password("cityslicka")
            .build();
}
