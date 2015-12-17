package models;

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Event extends Model
{
    @Id
    public Long id;
    public String eventname;
    public String date;
	public String time;
	
	public static Finder<Long, Event> find = new Finder<Long, Event>(Event.class);
}