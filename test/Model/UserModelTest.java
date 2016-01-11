package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import org.junit.*;

import play.libs.ws.WS;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

import static play.test.Helpers.*;
import static org.junit.Assert.*;


/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */
public class UserModelTest {
    @Test
    public void save() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                User user = new User();
                user.userName = "Test User Name";
                user.setPassword("pw");
                user.firstName = "Test First Name";
                user.lastName = "Test Last Name";
                user.save();
                assertTrue("User id test", user.id != null);
            }
        });
    }
}
