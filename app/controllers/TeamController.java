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
		//List<Event> events = Team.find.select("*").fetch("events");
		return ok(views.html.teams.show.render(team, users));
	}
	
	public Result update(Long id) {
		Form<Team> myForm = Form.form(Team.class).bindFromRequest();
		Team team = myForm.get();
		Team dbTeam = Team.find.byId(team.id);
		String n = team.getName();
		System.out.println(n);
		dbTeam.setName(n);
		dbTeam.update();
		return redirect(routes.TeamController.show(id));
	}
	
	public Result addMember(Long id) {
		Team team = Team.find.byId(id);
		DynamicForm requestData = Form.form().bindFromRequest();
		String result = requestData.get("userSelect");		
		Long userId = Long.valueOf(result).longValue();
		User user = User.find.byId(userId);
		team.members.add(user);
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
		
		team.delete();
		
		//Team.find.ref(id).delete();
		return redirect(routes.TeamController.teams());
	}
    
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
}