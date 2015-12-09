package models;

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Event extends Model
{
    @Id
    public String id;
    public String eventname;
    public String date;
}