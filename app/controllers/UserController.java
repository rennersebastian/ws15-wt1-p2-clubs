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
        return redirect(routes.UserController.users());
    }

    public Result users(){
        List<User> users = new Model.Finder<String, User>(User.class).all();
        return ok(views.html.Users.index.render(users));
    }

    public Result user(){
        return ok(views.html.Users.newUser.render());
    }

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
