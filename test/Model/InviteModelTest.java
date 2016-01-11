package Model;

import models.Event;
import models.Invite;
import models.Team;
import models.User;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

/**
 * Created by Sebastian on 11.01.2016.
 */
public class InviteModelTest {
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

                Team team = new Team();
                team.name = "Test Team";
                team.founded =  new Date(1220227200L * 1000L);
                team.save();

                Event event = new Event();
                event.eventname = "Test event";
                event.date = new Date(1220227200L * 1000L);
                event.team = team;
                event.save();

                Invite invite = new Invite();
                invite.event = event;
                invite.accept = 2;
                invite.invited = new Date(1220227200L * 1000L);
                invite.member = user;
                invite.save();

                assertTrue("Invite id test", invite.id != null);
            }
        });
    }
}
