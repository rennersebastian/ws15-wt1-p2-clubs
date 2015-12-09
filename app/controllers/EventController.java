package controllers;

import com.avaje.ebean.Model;
import models.Event;
import play.data.Form;
import play.*;
import play.mvc.*;
import views.html.*;
import java.util.List;
import static play.libs.Json.toJson;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class EventController {

    public Result newEvent(){
        Event event = Form.form(Event.class).bindFromRequest().get();
        event.save();
        return redirect(routes.Application.index());
    }

    public Result events(){
        List<Event> events = new Model.Finder<String, Event>(Event.class).all();
        return ok(toJson(events));
    }
}
