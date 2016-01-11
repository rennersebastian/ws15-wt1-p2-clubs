package models;

import com.avaje.ebean.Model;
import javax.persistence.*;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="or_event")
public class Event extends Model
{
    @Id
    public Long id;
    public String eventname;
    public Date date;
	
	public static Finder<Long, Event> find = new Finder<Long, Event>(Event.class);
	
	@ManyToOne
	public Team team;
	
	@OneToMany
	public List<Invite> invites;
}