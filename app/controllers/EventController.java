package controllers;

import com.avaje.ebean.Model;
import models.*;
import play.data.Form;
import play.*;
import play.mvc.*;
import play.mvc.Security.Authenticated;
import views.html.*;
import java.util.List;
import static play.libs.Json.toJson;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

@Authenticated(Secured.class)
public class EventController {

    public Result newEvent(){
        Event event = Form.form(Event.class).bindFromRequest().get();
        event.save();
        return redirect(routes.EventController.events());
    }

    public Result events(){
        List<Event> events = new Model.Finder<String, Event>(Event.class).all();
		List<Team> teams = new Model.Finder<String, Team>(Team.class).all();
        return ok(views.html.Events.index.render(events, teams));
    }
	
	public Result update(Long id){
		return redirect(routes.EventController.events());
	}
	
	public Result show(Long id) {
		Event event = Event.find.byId(id);
		return ok(views.html.Events.show.render(event));
	}
	
	public Result delete(Long id) {
		Event event = Event.find.byId(id);
		event.delete();
		return redirect(routes.EventController.events());
	}
}
