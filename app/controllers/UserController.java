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

public class UserController {

    public Result newUser(){
        User user = Form.form(User.class).bindFromRequest().get();
        user.save();
        return redirect(routes.Application.index());
    }

    public Result users(){
        List<User> users = new Model.Finder<String, User>(User.class).all();
        return ok(toJson(users));
    }
}
