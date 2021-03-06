package controllers;

import com.avaje.ebean.*;
import models.*;
import play.data.*;
import play.*;
import play.mvc.*;
import play.mvc.Security.Authenticated;
import views.html.*;
import java.util.*;
import static play.libs.Json.*;
import static play.mvc.Results.*;
import play.Logger;


@Authenticated(Secured.class)
public class TeamController {

    public Result newTeam(){
        Team team = Form.form(Team.class).bindFromRequest().get();
        team.save();
        return redirect(routes.TeamController.teams());
    }

    public Result teams(){
        List<Team> teams = new Model.Finder<String, Team>(Team.class).all();
        return ok(views.html.teams.index.render(teams));
    }
	
	public Result show(Long id) {
		Team team = Team.find.byId(id);
		List<User> users = User.find.all();
		List<User> members = team.members;
		users.removeAll(members);
		//List<Event> events = Team.find.select("*").fetch("events");
		
		List<Invite> accepts = new ArrayList<Invite>();
		List<Invite> declines = new ArrayList<Invite>();
		List<Invite> uncertainties = new ArrayList<Invite>();
		List<Invite> unansweredQuestions = new ArrayList<Invite>();
		Logger.info("" + team.getEvents().size());
		for (Event event : team.getEvents()) {
			Logger.info("EventId: " + event.id);
			event = Event.find.fetch("invites").where().eq("id", event.id).findUnique();
			Logger.info("Event-Invites: " + event.getInvites().size());
			Logger.info("" + event.getInvites().toString());
			/*accepts = event.getInvites().stream().filter(e -> e.getAccept() == Invite.AcceptType.ACCEPT.ordinal()).collect(Collectors.toList());
			declines = event.getInvites().stream().filter(e -> e.getAccept() == Invite.AcceptType.DECLINE.ordinal()).collect(Collectors.toList());
			uncertainties = event.getInvites().stream().filter(e -> e.getAccept() == Invite.AcceptType.UNCERTAIN.ordinal()).collect(Collectors.toList());
			unansweredQuestions = event.getInvites().stream().filter(e -> e.getAccept() == Invite.AcceptType.UNANSWERED.ordinal()).collect(Collectors.toList());*/
		}
		
		
		return ok(views.html.teams.show.render(team, users, accepts, declines, uncertainties, unansweredQuestions));
	}
	
	public Result update(Long id) {
		Form<Team> myForm = Form.form(Team.class).bindFromRequest();
		Team team = myForm.get();
		Team dbTeam = Team.find.byId(team.id);
		String n = team.getName();
		dbTeam.setName(n);
		dbTeam.update();
		return redirect(routes.TeamController.show(id));
	}
	
	public Result addMember(Long id) {
		Team team = Team.find.byId(id);
		DynamicForm requestData = Form.form().bindFromRequest();
		String result = requestData.get("userSelect");
		Logger.info(result);
		if(result != null && !result.isEmpty()) {
			Long userId = Long.valueOf(result).longValue();
			User user = User.find.byId(userId);
			team.members.add(user);
			team.save();
			
			for (Event event : team.getEvents()) {
				Invite invite = new Invite();
				invite.setAccept(Invite.AcceptType.UNANSWERED);
				invite.myevent = event;
				invite.member = user;
				invite.save();
				event.save();
			}
		}
		
		return redirect(routes.TeamController.show(id));
	}
	
	public Result removeMember(Long id, Long userId) {
		Team team = Team.find.byId(id);
		User user = User.find.byId(userId);
		team.members.remove(user);
		team.save();
		return redirect(routes.TeamController.show(id));
	}
	
	public Result members(Long id){
		Team team = Team.find.byId(id);
		List<User> memberList = team.members;
        return ok(views.html.teams.members.render(memberList));
    }
	
	public Result invites(Long id, Long eventId) {
		Team team = Team.find.byId(id);
		Event event = Event.find.byId(eventId);
		List<Invite> inviteList = event.invites;
		return ok(views.html.teams.invites.render(inviteList));
	}
	
	public Result delete(Long id) {
		Team team = Team.find.byId(id);
		team.getEvents().clear();
		team.getMembers().clear();
		team.save();
		team.delete();
		
		//Team.find.ref(id).delete();
		return redirect(routes.TeamController.teams());
	}
    
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
}