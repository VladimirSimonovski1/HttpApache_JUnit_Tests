package util;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class UserDataProvider {

    @DataProvider
    public static Object[][] getUserPerIdDataProvider() {
        return new Object[][]{
                {"1", "george.bluth@reqres.in", "George", "Bluth", "https://reqres.in/img/faces/1-image.jpg"},
                {"2", "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg"},
                {"3", "emma.wong@reqres.in", "Emma", "Wong", "https://reqres.in/img/faces/3-image.jpg"},
                {"4", "eve.holt@reqres.in", "Eve", "Holt", "https://reqres.in/img/faces/4-image.jpg"},
                {"5", "charles.morris@reqres.in", "Charles", "Morris", "https://reqres.in/img/faces/5-image.jpg"},
        };
    }

    @DataProvider
    public static Object[][] createUsersDataProvider() {
        return new Object[][]{
                {"Roman Reigns", "superman punch"},
                {"Seth Rollins", "curb stomp"},
                {"Dean Ambrose", "dirty deeds"},
        };
    }

    @DataProvider
    public static Object[][] createRegisterDataProvider() {
        return new Object[][]{
                {null, null, "Missing email or username"},
                {"eve.holt@reqres.in", null, "Missing password"},
                {null, "pistol", "Missing email or username"},
                {"test.test@test.com", "pistol", "Note: Only defined users succeed registration"},
        };
    }
}
