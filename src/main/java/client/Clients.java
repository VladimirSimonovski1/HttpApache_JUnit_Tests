package client;

import org.apache.http.HttpResponse;

import static config.Endpoints.CREATE_EMPLOYEE;
import static config.Endpoints.DELETE_EMPLOYEE;
import static config.Endpoints.GET_EMPLOYEES;
import static config.Endpoints.UPDATE_EMPLOYEE;

public class Clients extends Base {

    public HttpResponse getAllEmployees() throws Exception {
        return httpGet(GET_EMPLOYEES);
    }

    public HttpResponse createEmployee(String payload) throws Exception {
        return httpPost(CREATE_EMPLOYEE, payload);
    }

    public HttpResponse updateEmployee(String payload, int employee) throws Exception {
        return httpPut(UPDATE_EMPLOYEE + employee, payload);
    }

    public HttpResponse deleteEmployee(int employee) throws Exception {
        return httpDelete(DELETE_EMPLOYEE + employee);
    }
}
