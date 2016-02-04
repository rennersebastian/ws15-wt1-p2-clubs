package controllers;

import java.util.*;
import com.avaje.ebean.Model;
import models.*;
import play.data.Form;
import play.*;
import play.mvc.*;
import play.mvc.Security.Authenticated;
import views.html.*;
import play.mvc.Http.Session;

import java.util.Date;
import java.util.*;
import java.util.stream.*;
import static play.libs.Json.toJson;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;
import static play.mvc.Http.Context.Implicit.session;

@Authenticated(Secured.class)
public class EventController {
	
	public Result newEvent(Long id) {
		Event event = Form.form(Event.class).bindFromRequest().get();
		Team team = Team.find.byId(id);
		event.team = team;
		
		for (User member : team.members) {
			Invite invite = new Invite();
			invite.setAccept(Invite.AcceptType.UNANSWERED);
			invite.member = member;
			invite.myevent = event;
			invite.save();
				//event.getInvites().add(invite);
				
			Logger.info(invite.toString());
		}
		
		event.save();
		
		return redirect(routes.TeamController.show(id));
	}
	
	public Result events(Long id){
		Team team = Team.find.byId(id);
		List<Event> eventList = team.events;
        return ok(views.html.teams.events.render(eventList));
    }
	
	public Result updateName(Long id, Long eventId){
		Form<Event> myForm = Form.form(Event.class).bindFromRequest();
		Event event = myForm.get();
		Event dbEvent = Event.find.byId(event.id);
		String n = event.getEventName();
		dbEvent.setEventName(n);
		dbEvent.update();
		return redirect(routes.TeamController.show(id));
	}
	
	public Result updateDate(Long id, Long eventId){
		Form<Event> myForm = Form.form(Event.class).bindFromRequest();
		Event event = myForm.get();
		Event dbEvent = Event.find.byId(event.id);
		Date d = event.date;
		dbEvent.setDate(d);
		dbEvent.update();
		return redirect(routes.TeamController.show(id));
	}
	
	public Result show(Long id, Long eventId){
			
		Team team = Team.find.byId(id);
		
		String username = session().get("username");
		Logger.info(username);
		User user = User.findByUsername(username);
		List<User> members = team.members;
		members.remove(user);
		
		Event event = Event.find.byId(eventId);	
		List<Invite> accepts = event.getInvites().stream().filter(e -> e.getAccept() == Invite.AcceptType.ACCEPT.ordinal()).collect(Collectors.toList());
		List<Invite> declines = event.getInvites().stream().filter(e -> e.getAccept() == Invite.AcceptType.DECLINE.ordinal()).collect(Collectors.toList());
		List<Invite> uncertainties = event.getInvites().stream().filter(e -> e.getAccept() == Invite.AcceptType.UNCERTAIN.ordinal()).collect(Collectors.toList());
		List<Invite> unansweredQuestions = event.getInvites().stream().filter(e -> e.getAccept() == Invite.AcceptType.UNANSWERED.ordinal()).collect(Collectors.toList());
		
		return ok(views.html.Events.show.render(team, user, members, event, accepts, declines, uncertainties, unansweredQuestions));
	}
	
	public Result changeToAccept(Long userId, Long eventId) {
		updateAccept(eventId, Invite.AcceptType.ACCEPT);
		return redirect(routes.EventController.show(userId, eventId));
	}
	
	public Result changeToDecline(Long id, Long eventId) {
		updateAccept(eventId, Invite.AcceptType.DECLINE);
		return redirect(routes.EventController.show(id, eventId));
	}
	
	public Result changeToUncertain(Long id, Long eventId) {
		updateAccept(eventId, Invite.AcceptType.UNCERTAIN);
		return redirect(routes.EventController.show(id, eventId));
	}
	
	private void updateAccept(Long eventId, Invite.AcceptType type) {
		String username = session().get("username");
		User user = User.findByUsername(username);
		Event event = Event.find.byId(eventId);
		
		Invite invite = Invite.find.where().eq("member", user).eq("myevent", event).findUnique();
		Logger.info("Invite: " + invite.toString() + " - " + invite.member.toString() + " user: " + user.invites);
		invite.setAccept(type);
		invite.save();
		Logger.info("" + invite.getAccept());
	}
	
	public Result delete(Long id, Long eventId){
		//Event.find.ref(eventId).delete();
		Event event = Event.find.byId(eventId);
		/*event.team = null;
		event.invites.clear();
		event.save();*/
		event.delete();
		//Team team = Team.find.byId(id);
		//team.events.remove(event);
		//team.save();
		//event.delete();
		return redirect(routes.TeamController.show(id));
	}
}
