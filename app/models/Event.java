package models;

import com.avaje.ebean.Model;
import javax.persistence.*;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="or_event")
public class Event extends Model {
    @Id
	@Column(name="event_id")
    public Long id;
	
	@Constraints.Required
	@Column(name = "event_name")
    public String eventName;

	@Constraints.Required
    public Date date;
	
	public static Finder<Long, Event> find = new Finder<Long, Event>(Event.class);
	
	@ManyToOne
	public Team team;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "myevent")
	public List<Invite> invites;
	
	public String getEventName() { return this.eventName; }
	public Date getDate() { return this.date; }
	public List<Invite> getInvites() { return this.invites; }
	
	public void setEventName(String name) {
		this.eventName = name;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}