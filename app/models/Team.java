package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="or_team")
public class Team extends Model {
    @Id
	@Column(name="team_id")
    public Long id;
    
	@Constraints.Required
    public String name;
	
	@Formats.DateTime(pattern="dd.MM.yyyy")
    public Date founded = new Date();
	
	public static Finder<Long, Team> find = new Finder<Long,Team>(Team.class);
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "team")
	public List<Event> events;
	
	@ManyToMany
	public List<User> members = new ArrayList<User>();
	
	public String getName() { return this.name; }
	public Date getFounded() { return this.founded; }
	public List<Event> getEvents() { return this.events; }
	public List<User> getMembers() { return this.members; }

    public void setName(String name) {
        this.name = name;
    }
	
	public void setFounded(Date founded) {
		this.founded = founded;
	}
}