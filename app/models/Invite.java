package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="or_invite")
public class Invite extends Model{

	public static enum AcceptType {
		ACCEPT,
		DECLINE,
		UNCERTAIN,
		UNANSWERED
	}
	
	public Invite() {
		invited = new Date();
	}

    @Id
	@Column(name = "invite_id")
    public Long id;
    
	@Constraints.Required
    public Integer accept;
	
	@Formats.DateTime(pattern="dd.MM.yyyy")
    public Date invited = new Date();
	
	public static Finder<Long, Invite> find = new Finder<Long,Invite>(Invite.class);
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Event event;
	
	@ManyToOne(cascade=CascadeType.ALL)
	public User member;
	
	public Integer getAccept() { return this.accept; }
	public Date getInvited() { return this.invited; }
	
	public void setAccept(AcceptType type) {
		this.accept = type.ordinal();
	}
	
	public void setInvited(Date invited) {
		this.invited = invited;
	}
}