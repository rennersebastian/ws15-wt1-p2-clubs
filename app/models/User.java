package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model{
    @Id
    public Long id;

    public String userName;
    public String firstName;
    public String lastName;

    public static Finder<Long, User> find = new Finder<Long,User>(User.class);
}
