package models;

import com.avaje.ebean.Model;
import javax.persistence.*;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="or_user")
public class User extends Model{
    @Id
	@Column(name = "user_id")
    public Long id;

    @Constraints.Required
	@Column(name = "user_name")
    public String userName;

    @Constraints.Required
    public byte[] shaPassword;

	@Constraints.Required
	@Column(name = "first_name")
    public String firstName;
	
	@Constraints.Required
	@Column(name = "last_name")
    public String lastName;

    public static Finder<Long, User> find = new Finder<Long,User>(User.class);

    public Long getId(){ return this.id; }
    public String getUserName() { return this.userName; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public byte[] getShaPassword() { return this.shaPassword; }
	
	public void setUsername(String username) {
        this.userName = username;
    }
	
    public void setFirstName(String firstname) { 
		this.firstName = firstname;
	}
	
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
	
	public void setPassword(String password) {
        this.shaPassword = getSha512(password);
    }
	
    public void setShaPassword(byte[] shaPassword) { 
		this.shaPassword = shaPassword;
	}

    public static byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static User findByUsernameAndPassword(String username, String password) {
        return find
                .where()
                .eq("user_name", username)
                .eq("sha_password", getSha512(password))
                .findUnique();
    }

    public static User findByUsername(String username) {
        return find
                .where()
                .eq("user_name", username)
                .findUnique();
    }

	@ManyToMany(cascade=CascadeType.ALL)
	public List<Team> teams = new ArrayList<Team>();

	@OneToMany(cascade=CascadeType.ALL, mappedBy = "member")
	public List<Invite> invites;
	
	public List<Invite> getInvites() { return this.invites; }
}
