package Controller;

import com.sun.media.jfxmedia.logging.Logger;
import controllers.routes;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.api.Application;
import play.api.routing.Router;
import play.api.test.FakeRequest;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;
import play.test.WithBrowser;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static play.test.Helpers.*;

/**
 * Created by Sebastian on 01.02.2016.
 */
public class UserControllerTest extends WithApplication {
    private static play.test.FakeApplication fakeApplication;

    @BeforeClass
    public static void startFakeApplication() {
        fakeApplication = fakeApplication();
        start(fakeApplication);
    }

    @AfterClass
    public static void shutdownFakeApplication() {
        stop(fakeApplication);
    }

    @Test
    public void testIndex() {
        play.mvc.Result result = Helpers.route(routes.Application.index());
        assertEquals(200, result.status());
    }

    @Test
    public void testSignUp() {
        String username = "testUser";
        String firstName = "first";
        String lastName = "last";
        String password = "secret";

        Map<String, String> userData = new HashMap<String, String>();
        userData.put("username", username);
        userData.put("password", password);
        userData.put("firstName", firstName);
        userData.put("lastName", lastName);

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/user")
                .bodyForm(userData);

        Result result = route(request);
        Logger.logMsg(1, result.redirectLocation());
        System.out.println(result.redirectLocation());
        assertEquals(303, result.status());
        assertEquals("/users", result.redirectLocation());
    }
}
