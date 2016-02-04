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
	
	public static Map<AcceptType, String> symbols = new HashMap<AcceptType, String>();
	
	public Invite() {
		invited = new Date();
		setAccept(AcceptType.UNANSWERED);
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
	public Event myevent;
	
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
	
	public static String getAcceptSymbol(Long memberId, Long eventId) {
        Invite invite = Invite.find
							.where()
							.eq("myevent.id", eventId)
							.eq("member.id", memberId)
							.findUnique();
		//AcceptType.values()[invite.getAccept()];
		
		switch(AcceptType.values()[invite.getAccept()]) {
			case ACCEPT: {
				return "glyphicon glyphicon-ok-sign";
			}
			case DECLINE: {
				return "glyphicon glyphicon-remove-sign";
			}
			case UNCERTAIN: {
				return "glyphicon glyphicon-question-sign";
			}
			case UNANSWERED: {
				return "glyphicon glyphicon-info-sign";
			}
		}
		return "";
    }
}