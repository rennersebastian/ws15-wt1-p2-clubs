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

    public Result users(){
        List<User> users = new Model.Finder<String, User>(User.class).all();
        return ok(views.html.Users.index.render(users));
    }

    public Result user(){ return ok(views.html.Users.newUser.render()); }

    public Result show(Long id){
        User user = User.find.byId(id);
        return ok(views.html.Users.show.render(user));
    }

    public Result delete(Long id) {
        User user = User.find.byId(id);
        user.delete();
        return redirect(routes.UserController.users());
    }
    public Result update(Long id) {
        Form<User> myForm = Form.form(User.class).bindFromRequest();
        User user = myForm.get();
        User dbUser = User.find.byId(user.id);
        String n = user.userName;
        System.out.println(n);
        //dbTeam.setName(n);
        dbUser.update();
		/*Team dbTeam = Team.find.byId(1L);

		Form<Team> myForm = Form.form(Team.class).bindFromRequest();
		myForm.get().update(String.parse(dbTeam.id));

		//dbTeam.setName(myForm.apply("name").value());
		//dbTeam.save();*/
        return redirect(routes.UserController.show(user.id));
    }
}
