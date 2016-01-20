package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="or_team")
public class Team extends Model{
    @Id
    public Long id;
    
	@Constraints.Required
    public String name;
	
	@Formats.DateTime(pattern="dd.MM.yyyy")
    public Date founded = new Date();
	
	public static Finder<Long, Team> find = new Finder<Long,Team>(Team.class);
	
	@OneToMany
	public List<Event> events;
	
	@ManyToMany(cascade=CascadeType.ALL)
	public List<User> members;
	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}