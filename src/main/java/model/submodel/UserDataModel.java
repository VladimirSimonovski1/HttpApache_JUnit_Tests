package model.submodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDataModel {

    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
