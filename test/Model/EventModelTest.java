package Model;

import models.Event;
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
public class EventModelTest {
    @Test
    public void save() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Team team = new Team();
                team.name = "Test Team";
                team.founded =  new Date(1220227200L * 1000L);
                team.save();

                Event event = new Event();
                event.eventname = "Test event";
                event.date = new Date(1220227200L * 1000L);
                event.team = team;
                event.save();

                assertTrue("Event id test", event.id != null);
            }
        });
    }
}
