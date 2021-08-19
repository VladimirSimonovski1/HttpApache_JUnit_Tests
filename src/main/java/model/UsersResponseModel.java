package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.submodel.SupportModel;
import model.submodel.UserDataModel;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseModel {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<UserDataModel> Data;
    private SupportModel support;
}
