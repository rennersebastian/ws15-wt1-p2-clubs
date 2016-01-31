import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
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

import models.*;
import java.util.*;

import static play.test.Helpers.*;
import static org.junit.Assert.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertEquals(2, a);
    }

    @Test
    public void testInServer() {
        int timeout = 5000;
        running(testServer(3333), () -> {
            assertEquals(OK, WS.url("http://localhost:3333").get().get(timeout).getStatus());
        });
    }
	
	@Test
    public void teamEvents() {
		Team team = new Team();
		team.name = "Test Team Name";
		team.founded = new Date();	
		//team.events = new ArrayList<Event>();
		team.save();
		
		Event event = new Event();
		event.eventName = "Test Event Name";
		event.date = new Date();
		event.team = team;
		event.save();
		
		User user = new User();
		user.setUserName("Mustermann");
		user.setPassword("secret");
		user.teams.add(team);
		user.save();
		
		Invite invite = new Invite();
		invite.setAccept(Invite.AcceptType.ACCEPT);
		invite.invited = new Date();
		invite.event = event;
		invite.member = user;
		
		List<Event> teamevents = team.events; //Team.find.select("*").fetch("events").findList();
		List<Team> joinedTeams = user.teams;
		
		assertTrue("teamevents == 1", teamevents.size() == 1);
		assertTrue("joinedTeams == 1", joinedTeams.size() == 1);
		assertTrue("event.invites == 1", event.invites.size() == 1);
		assertTrue("team.members == 1", team.members.size() == 1);
		assertTrue("user.invites == 1", user.invites.size() == 1);
		
		assertTrue("Team id test", team.id != null);
		assertTrue("Event id test", event.id != null);
		assertNotNull(event.team);
		assertNotNull(event.invites);
		assertNotNull(team.events);
		assertNotNull(team.members);
		assertNotNull(user.teams);
		assertNotNull(user.invites);
		assertNotNull(invite.event);
		assertNotNull(invite.member);
		
	}

}
