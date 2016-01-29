package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.User;
import play.data.Form;
import play.*;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.Session;
import play.mvc.Security.Authenticated;
import views.html.*;
import java.util.List;

import static play.mvc.Http.Context.Implicit.flash;
import static play.mvc.Http.Context.Implicit.session;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class UserController {

    public Result newUser(){
        Form<SignUp> signUpForm = Form.form(SignUp.class).bindFromRequest();

        if ( signUpForm.hasErrors()) {
            flash().put("error", "Inputs are not correct");
            return redirect(routes.UserController.user());
        }
        SignUp newUser =  signUpForm.get();
        User existingUser = User.findByUsername(newUser.username);
        if(existingUser != null) {
            flash().put("error", "Username already exists.");
            return redirect(routes.UserController.user());
        } else {
            User user = new User();
            user.setUsername(newUser.username);
            user.setPassword(newUser.password);
            user.setFirstName(newUser.firstName);
            user.setLastName(newUser.lastName);
            user.save();
            session().clear();
            session().put("username", newUser.username);

            return redirect(routes.UserController.users());
        }
    }

    public static class UserForm {
        @Constraints.Required
        public String username;

        public String firstName;
        public String lastName;
    }

    public static class SignUp extends UserForm {
        @Constraints.Required
        public String password;
    }

    private static ObjectNode buildJsonResponse(String type, String message) {
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put("message", message);
        wrapper.put(type, msg);
        return wrapper;
    }


    @Authenticated(Secured.class)
    public Result users(){
        List<User> users = new Model.Finder<String, User>(User.class).all();
        return ok(views.html.Users.index.render(users));
    }

    public Result user(){ return ok(views.html.Users.newUser.render()); }

    @Authenticated(Secured.class)
    public Result showByName(String username){
        User user = User.findByUsername(username);
        return ok(views.html.Users.show.render(user));
    }

    @Authenticated(Secured.class)
    public Result show(Long id){
        User user = User.find.byId(id);
        return ok(views.html.Users.show.render(user));
    }


    @Authenticated(Secured.class)
    public Result delete(Long id) {
        User user = User.find.byId(id);
        user.delete();
        return redirect(routes.UserController.users());
    }


    @Authenticated(Secured.class)
    public Result update(Long id) {
        Form<User> myForm = Form.form(User.class).bindFromRequest();
        User user = myForm.get();
        User dbUser = User.find.byId(user.id);

        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setShaPassword(user.getShaPassword());
        dbUser.save();

        return redirect(routes.UserController.show(user.id));
    }
}
