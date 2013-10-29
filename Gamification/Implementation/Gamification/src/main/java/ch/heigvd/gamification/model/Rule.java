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
                name = "findAllRules",
                query = "SELECT r FROM Rule r"
        )
)

@Entity
public class Rule implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "rule")
    private int id;
    private String onEventType;
    private int numberOfPoints;
    @ManyToOne
    private Application application;
    /*@OneToOne(mappedBy="badge", fetch = FetchType.LAZY)
    private Badge badge;*/
    
    public Rule () {}
    public Rule (String event, int points, Application app){
        this.onEventType = event;
        this.numberOfPoints = points;
        this.application = app;
        /*this.badge = badge;*/
    }
    
    public int getId (){
        return id;
    }
    
    public String getOnEventType() {
        return onEventType;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }
    
    public Application getApplication(){
        return this.application;
    }
    
    public void setOnEventType(String onEventType) {
        this.onEventType = onEventType;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }
    
    public void setApplication(Application application){
        this.application = application;
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
        return "ch.heigvd.gamification.model.Rule[ id=" + this.id + " ]";
    }
}
