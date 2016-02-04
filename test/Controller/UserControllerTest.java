package Controller;

import com.sun.media.jfxmedia.logging.Logger;
import controllers.UserController;
import controllers.routes;
import models.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import play.api.Application;
import play.api.routing.Router;
import play.api.test.FakeRequest;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;
import play.test.WithBrowser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Context.Implicit.session;
import static play.test.Helpers.*;

public class UserControllerTest extends WithApplication {
    private static UserController userController;
    private static Http.Context context;

    @BeforeClass
    public static void setup() {
        userController = new UserController();
        context = new Http.Context(fakeRequest());
        Http.Context.current.set(context);
    }

    @Test
    public void testIndex() {
        play.mvc.Result result = Helpers.route(routes.Application.index());
        assertEquals(200, result.status());
    }

    @Test
    public void testUsersRoute() {
        play.mvc.Result result = Helpers.route(routes.UserController.user());
        assertEquals(200, result.status());
    }

    @Test
    public void testNewUser() {
        String username = "testUser";
        String firstName = "first";
        String lastName = "last";
        String password = "secret";

        Map<String, String> userData = new HashMap<String, String>();
        userData.put("username", username);
        userData.put("password", password);
        userData.put("firstName", firstName);
        userData.put("lastName", lastName);

        Http.RequestBuilder newUserRequest = new Http.RequestBuilder()
                .method(POST)
                .uri("/user")
                .bodyForm(userData);

        Result newUserResult = route(newUserRequest);

        assertEquals(303, newUserResult.status());
        assertEquals("/users", newUserResult.redirectLocation());
    }
}