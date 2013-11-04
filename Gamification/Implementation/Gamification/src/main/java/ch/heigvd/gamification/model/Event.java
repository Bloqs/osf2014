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

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "event")
    private Long id;
    private String type;
    @ManyToOne
    private Application application;
    @ManyToOne
    private Player player;

    public Event() {
    }

    public Event(Event eventData) {
        this.type = eventData.type;
        this.application = eventData.application;
        this.player = eventData.player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Event)) {
            return false;
        } else {
            Event other = (Event) object;
            if (this.id == other.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ch.heigvd.gamification.model.Event[ id=" + this.id + " ]";
    }

}
