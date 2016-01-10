package models;

import com.avaje.ebean.Model;
import javax.persistence.*;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="or_user")
public class User extends Model{
    @Id
    public Long id;

    public String userName;
    public String firstName;
    public String lastName;

    public static Finder<Long, User> find = new Finder<Long,User>(User.class);
	
	@ManyToMany(cascade=CascadeType.ALL)
	public List<Team> teams;
	
	@OneToMany
	public List<Invite> invites;
}
