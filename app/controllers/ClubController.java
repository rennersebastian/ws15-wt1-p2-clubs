package controllers;

import com.avaje.ebean.Model;
import models.Club;
import play.data.Form;
import play.*;
import play.mvc.*;
import views.html.*;
import java.util.List;
import static play.libs.Json.toJson;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class ClubController {

    public Result newClub(){
        Club club = Form.form(Club.class).bindFromRequest().get();
        club.save();
        return redirect(routes.ClubController.clubs());
    }

    public Result clubs(){
        List<Club> clubs = new Model.Finder<String, Club>(Club.class).all();
        return ok(views.html.Clubs.index.render(clubs));
        //return ok(toJson(clubs));
    }
    
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
}
