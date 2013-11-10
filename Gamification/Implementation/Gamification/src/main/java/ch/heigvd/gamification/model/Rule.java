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

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "rule")
    private Long id;
    
    private String onEventType;
    private int numberOfPoints;
    @ManyToOne
    private Application application;
    @ManyToOne
     private Badge badge;

    public Rule() {
        onEventType = "UNDEF";
        numberOfPoints = -1;
        application = null;
    }

    public Rule(Rule ruleData) {
        this.onEventType = ruleData.onEventType;
        this.numberOfPoints = ruleData.numberOfPoints;
        this.application = ruleData.application;
        this.badge = ruleData.badge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOnEventType() {
        return onEventType;
    }

    public void setOnEventType(String onEventType) {
        this.onEventType = onEventType;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the apiKey fields are not set
        if (!(object instanceof Rule)) {
            return false;
        }
        Rule other = (Rule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.heigvd.gamification.model.Rule[ id=" + this.id + " ]";
    }
}
