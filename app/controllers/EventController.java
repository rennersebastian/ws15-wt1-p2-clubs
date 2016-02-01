package controllers;

import java.util.*;
import com.avaje.ebean.Model;
import models.*;
import play.data.Form;
import play.*;
import play.mvc.*;
import play.mvc.Security.Authenticated;
import views.html.*;

import java.util.Date;
import java.util.List;
import static play.libs.Json.toJson;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

@Authenticated(Secured.class)
public class EventController {
	
	public Result newEvent(Long id) {
		Event event = Form.form(Event.class).bindFromRequest().get();
		Team team = Team.find.byId(id);
		event.team = team;
        event.save();
		return redirect(routes.TeamController.show(id));
	}
	
	public Result events(Long id){
		Team team = Team.find.byId(id);
		List<Event> eventList = team.events;
        return ok(views.html.teams.events.render(eventList));
    }

    /*public Result events(){
        List<Event> events = new Model.Finder<String, Event>(Event.class).all();
		List<Team> teams = new Model.Finder<String, Team>(Team.class).all();
        return ok(views.html.Events.index.render(events, teams));
    }*/
	
	public Result updateName(Long id, Long eventId){
		Form<Event> myForm = Form.form(Event.class).bindFromRequest();
		Event event = myForm.get();
		Event dbEvent = Event.find.byId(event.id);
		String n = event.getEventName();
		dbEvent.setEventName(n);
		dbEvent.update();
		return redirect(routes.EventController.events(id));
	}
	
	public Result updateDate(Long id, Long eventId){
		Form<Event> myForm = Form.form(Event.class).bindFromRequest();
		Event event = myForm.get();
		Event dbEvent = Event.find.byId(event.id);
		Date d = event.date;
		dbEvent.setDate(d);
		dbEvent.update();
		return redirect(routes.EventController.events(id));
	}
	
	public Result show(Long id, Long eventId){
		Event event = Event.find.byId(eventId);
		Team team = Team.find.byId(id);
		return ok(views.html.Events.show.render(team, event));
	}
	
	public Result delete(Long id, Long eventId){
		Event event = Event.find.byId(eventId);
		event.delete();
		return redirect(routes.EventController.events(id));
	}
}
