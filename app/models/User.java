package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model{
    @Id
    public String id;
    public String username;
    public String firstName;
    public String lastName;
}
