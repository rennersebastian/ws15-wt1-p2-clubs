package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
public class Club extends Model{
    @Id
    public String id;
    
    public String name;
}