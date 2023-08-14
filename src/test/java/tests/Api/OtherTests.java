package tests.Api;

import org.testng.annotations.Test;
import resources.RestApiMethods;

import static resources.RestApiMethods.*;

public class OtherTests {

    @Test
    public void getInfoAboutMe() throws InterruptedException {
        //This test only for check valid token
        RestApiMethods.getInfoUser();
    }

    @Test
    public void checkGetCard() {
        //test for fail
        getCard("64da38d28fe02c8d11c6c0e5");
    }
}

