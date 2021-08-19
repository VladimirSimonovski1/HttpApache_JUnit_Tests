package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.submodel.SupportModel;
import model.submodel.UserDataModel;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel {

    private UserDataModel Data;
    private SupportModel support;
}
