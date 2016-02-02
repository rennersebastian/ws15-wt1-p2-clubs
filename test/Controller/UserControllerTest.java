package Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.routes;
import models.User;
import org.apache.http.protocol.HTTP;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.api.mvc.Session;
import play.api.test.FakeApplication;
import play.api.test.FakeRequest;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static play.mvc.Http.Status.OK;
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
}
