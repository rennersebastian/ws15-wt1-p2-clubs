package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
public class User extends Model{
    @Id
    public Long id;

    @Constraints.Required
    public String userName;

    @Constraints.Required
    private byte[] shaPassword;

    public String firstName;
    public String lastName;

    public static Finder<Long, User> find = new Finder<Long,User>(User.class);

    public Long getId(){
        return id;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setPassword(String password) {
        this.shaPassword = getSha512(password);
    }
    public void setFirstName(String firstname) { this.firstName = firstname; }
    public void setLastName(String lastname) {
        this.lastName = lastname;
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

    public static User findByUsername(String username) {
        return find
                .where()
                .eq("user_name", username)
                .findUnique();
    }
}
