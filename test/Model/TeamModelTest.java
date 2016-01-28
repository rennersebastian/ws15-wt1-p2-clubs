package Model;

import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import models.Team;
import models.Event;
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
public class TeamModelTest {
    /**@Test
    public void save() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Team team = new Team();
                team.name = "Test Team Name";
                team.founded = new Date();				
                team.save();
				
				Event event = new Event();
				event.eventname = "Test Event Name";
				event.date = new Date();
				event.team = team;
				event.save();
				
                assertTrue("Team id test", team.id != null);
				assertTrue("Event id test", event.id != null);
				assertTrue("Event contains Team test", event.team != null);
				assertTrue("Team contains Event test", team.events.size > 0);
            }
        });
    }*/
}