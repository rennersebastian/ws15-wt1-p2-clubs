package controllers;

import com.avaje.ebean.Model;
import models.User;
import play.data.Form;
import play.*;
import play.mvc.*;
import views.html.*;
import java.util.List;
import static play.libs.Json.toJson;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class LoginController {
    public Result index(){
        return ok(views.html.Users.login.render());
    }
}
