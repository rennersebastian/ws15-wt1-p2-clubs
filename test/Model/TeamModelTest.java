package Model;

import models.Event;
import models.Team;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

/**
 * Created by Sebastian on 11.01.2016.
 */
public class TeamModelTest {
    @Test
    public void save() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Team team = new Team();
                team.name = "Test Team";
                team.founded =  new Date(1220227200L * 1000L);
                team.save();

                assertTrue("Team id test", team.id != null);
            }
        });
    }
}
