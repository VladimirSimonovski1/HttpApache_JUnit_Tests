import assertions.TestAssertions;
import client.Clients;
import lombok.extern.java.Log;
import model.PostEmployeeRequestModel;
import org.apache.http.HttpResponse;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static util.ObjectMapperConversion.objectToString;

@Log
public class ApacheJUnitTests {

    private Clients clients = new Clients();
    private TestAssertions testAssertions = new TestAssertions();
    private PostEmployeeRequestModel postEmployeeRequestModel = PostEmployeeRequestModel.builder()
            .name("Vladimir")
            .salary("200000")
            .age("27")
            .build();

    @Test
    public void shouldGetAllEmployees() throws Exception {
        HttpResponse response = clients.getAllEmployees();
        log.info("Create request: " + response);

        int statusCode = response.getStatusLine().getStatusCode();
        assertThat(statusCode, is(equalTo(SC_OK)));

        testAssertions.assertSuccessfulPostInjuryEventResponse(response);
    }

    @Test
    public void shouldCreateEmployee() throws Exception {
        String creteEmployeeRequestBody = objectToString(postEmployeeRequestModel);

        HttpResponse actualResponse = clients.createEmployee(creteEmployeeRequestBody);

        assertThat(actualResponse.getStatusLine().getStatusCode(), is(equalTo(SC_OK)));
    }

}
