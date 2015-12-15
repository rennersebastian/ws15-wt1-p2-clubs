package models;

import com.avaje.ebean.Model;
import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Team extends Model{
    @Id
    public Long id;
    
	@Constraints.Required
    public String name;
	
	@Formats.DateTime(pattern="dd.MM.yyyy")
    public Date founded = new Date();
	
	public static Finder<Long, Team> find = new Finder<Long,Team>(Team.class);
}