package payload;

import model.UserRequestModel;

public class UserRequestPayload {

    public static UserRequestModel userRequestBody = UserRequestModel.builder()
            .name("Vladimir")
            .job("QA")
            .build();
}
