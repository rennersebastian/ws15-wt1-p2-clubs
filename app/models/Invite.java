package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="or_invite")
public class Invite extends Model{
    @Id
    public Long id;
    
	@Constraints.Required
    public Integer accept;
	
	@Formats.DateTime(pattern="dd.MM.yyyy")
    public Date invited = new Date();
	
	public static Finder<Long, Invite> find = new Finder<Long,Invite>(Invite.class);
	
	@ManyToMany(cascade=CascadeType.ALL)
	public List<Event> events;
	
	@ManyToMany(cascade=CascadeType.ALL)
	public List<User> members;
}