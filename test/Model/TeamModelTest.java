package Model;

import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import models.Invite;
import models.Team;
import models.Event;
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
public class TeamModelTest extends WithApplication{/**
    @Test
    public void save() {
		 Team team = new Team();
		 team.name = "Test Team Name";
		 team.founded = new Date(1220227200L * 1000);
		 //team.events = new ArrayList<Event>();
		 team.save();

		 Event event = new Event();
		 event.eventName = "Test Event Name";
		 event.date = new Date();
		 event.team = team;
		 event.save();

		 User user = new User();
		 user.setUsername("Mustermann");
		 user.setPassword("secret");
		 user.setFirstName("Max");
		 user.setLastName("Mustermann");
		 user.teams.add(team);
		 user.save();

		 Invite invite = new Invite();
		 invite.setAccept(Invite.AcceptType.ACCEPT);
		 invite.invited = new Date();
		 invite.myevent = event;
		 invite.member = user;

		 List<Event> teamevents = team.events; //Team.find.select("*").fetch("events").findList();
		 List<Team> joinedTeams = user.teams;

		 //assertTrue("teamevents == 1", teamevents.size() == 1);
		 assertTrue("joinedTeams == 1", joinedTeams.size() == 1);
		 //assertTrue("event.invites == 1", event.invites.size() == 1);
		 //assertTrue("team.members == 1", team.members.size() == 1);
		 //assertTrue("user.invites == 1", user.invites.size() == 1);

		 assertTrue("Team id test", team.id != null);
		 assertTrue("Event id test", event.id != null);
		 assertNotNull(event.team);
		 assertNotNull(event.invites);
		 assertNotNull(team.events);
		 assertNotNull(team.members);
		 assertNotNull(user.teams);
		 assertNotNull(user.invites);
		 assertNotNull(invite.myevent);
		 assertNotNull(invite.member);
    }*/
}