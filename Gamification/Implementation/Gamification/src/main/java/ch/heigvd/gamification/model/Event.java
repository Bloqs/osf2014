/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Jean-Luc
 */
@NamedQueries(
        @NamedQuery(
                name = "findAllEvents",
                query = "SELECT e FROM Event e"
        )
)

@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "event")
    private int id;
    private String type;
    @ManyToOne
    private Application application;
    @ManyToOne
    private Player player;
    
    public Event(){}
    public Event(String type, Application app, Player player){
        this.type = type;
        this.application = app;
        this.player = player;
    }
    
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Application getApplication() {
        return application;
    }

    public Player getPlayer() {
        return player;
    }
    
    
    public void setType(String type) {
        this.type = type;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    @Override
    public int hashCode(){
        return this.id;
    }
        
    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Rule)) {
            return false;
        }
        else{
            Rule other = (Rule) object;
            if (this.id == other.getId() ) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ch.heigvd.gamification.model.Event[ id=" + this.id + " ]";
    }
    
}
