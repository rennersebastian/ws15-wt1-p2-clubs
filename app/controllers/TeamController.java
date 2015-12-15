package controllers;

import com.avaje.ebean.*;
import models.*;
import play.data.*;
import play.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import static play.libs.Json.toJson;
import static play.mvc.Results.*;

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
		return ok(views.html.teams.show.render(team));
	}
	
	public Result update(Long id) {
		Form<Team> myForm = Form.form(Team.class).bindFromRequest();
		Team team = myForm.get();
		Team dbTeam = Team.find.byId(team.id);
		String n = team.name;
		System.out.println(n);
		dbTeam.setName(n);
		dbTeam.update();
		/*Team dbTeam = Team.find.byId(1L);
		
		Form<Team> myForm = Form.form(Team.class).bindFromRequest();
		myForm.get().update(String.parse(dbTeam.id));
		
		//dbTeam.setName(myForm.apply("name").value());
		//dbTeam.save();*/
		return redirect(routes.TeamController.show(1L));
	}
	
	public Result delete(Long id) {
		Team team = Team.find.byId(id);
		team.delete();
		return redirect(routes.TeamController.teams());
	}
    
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
}