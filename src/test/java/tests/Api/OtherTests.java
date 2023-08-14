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

    @Test
    public void checkGetColumn() {
        //test for fail
        getColumn("64da6e3439d127ed04293f70");
    }
}

