package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.User;
import play.data.Form;
import play.*;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import java.util.List;
import static play.libs.Json.toJson;
import static play.mvc.Http.Context.Implicit.flash;
import static play.mvc.Http.Context.Implicit.session;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;
import static play.mvc.Results.unauthorized;

public class LoginController {
    public Result index(){
        return ok(views.html.Users.login.render());
    }

    public Result loginUser() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            flash().put("error", "Password or username is not correct");
            return redirect(routes.LoginController.index());
        }
        Login loggingInUser = loginForm.get();
        User user = User.findByUsernameAndPassword(loggingInUser.username.toLowerCase(), loggingInUser.password);
        if(user == null) {
            flash().put("error", "Password or username is not correct");
            return redirect(routes.LoginController.index());
        } else {
            session().clear();
            session().put("username", loggingInUser.username.toLowerCase());

            flash().put("success", "Welcome " + loggingInUser.username.toLowerCase());
            return redirect(routes.Application.index());
        }
    }


    public Result logout() {
        session().clear();
        flash().put("success", "Logged out successfully");
        return redirect(routes.Application.index());
    }

    public Result isAuthenticated() {
        if(session().get("username") == null) {
            return unauthorized();
        } else {
            ObjectNode wrapper = Json.newObject();
            ObjectNode msg = Json.newObject();
            msg.put("message", "User is logged in already");
            msg.put("user", session().get("username"));
            wrapper.put("success", msg);
            return ok(wrapper);
        }
    }

    public Result getCurrentUser(){
        return ok(session().get("username"));
    }

    public static class UserForm {
        @Constraints.Required
        public String username;
    }

    public static class Login extends UserForm {
        @Constraints.Required
        public String password;
    }
}
