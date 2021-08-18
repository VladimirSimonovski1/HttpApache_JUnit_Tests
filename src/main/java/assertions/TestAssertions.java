package assertions;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.java.Log;
import model.GetEmployeeResponseModel;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static util.ObjectMapperConversion.JSONToObject;

@Log
public class TestAssertions {

    public void assertSuccessfulPostInjuryEventResponse(HttpResponse response) throws IOException {
        GetEmployeeResponseModel getEmployeeResponseBody = getEmployeeResponseBody(response);
        log.info("Response body is: " + getEmployeeResponseBody.toString());

        assertThat(response.getStatusLine().getStatusCode(), equalTo(SC_OK));
        assertThat(getEmployeeResponseBody, allOf(
                hasProperty("status", is(equalTo("success"))),
                hasProperty("data", hasItem(allOf(
                        hasProperty("id", is(equalTo(1))),
                        hasProperty("employee_name", is(equalTo("Tiger Nixon"))),
                        hasProperty("employee_salary", is(equalTo(320800))),
                        hasProperty("employee_age", is(equalTo(61)))
                        )
                ))));
    }

    public static GetEmployeeResponseModel getEmployeeResponseBody(HttpResponse response) throws IOException {
        GetEmployeeResponseModel injuryEventResponseBodyCommon;
        String body = EntityUtils.toString(response.getEntity());
        injuryEventResponseBodyCommon = JSONToObject(body, GetEmployeeResponseModel.class);

        return injuryEventResponseBodyCommon;
    }

    public static ResponseSpecification status200Ok() {
        return new ResponseSpecBuilder()
                .expectContentType("application/json;charset=utf-8")
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification status201Created() {
        return new ResponseSpecBuilder()
                .expectContentType("application/json")
                .expectStatusCode(200)
                .build();
    }
}
