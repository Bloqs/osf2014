/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.to;

import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jean-Luc
 */
@XmlRootElement
public class PublicRuleTO {

    private Long id;
    private String onEventType;
    private int numberOfPoints;
    private PublicBadgeTO badge;
    //private PublicApplicationTO applicationTO;

    public PublicRuleTO() {
    }

    public PublicRuleTO(long id, String event, int points, PublicBadgeTO badge/*, PublicApplicationTO app*/) {
        this.onEventType = event;
        this.numberOfPoints = points;
        this.id = id;
        this.badge = badge;
        //this.applicationTO = app;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PublicBadgeTO getBadge() {
        return badge;
    }

    public void setBadge(PublicBadgeTO badge) {
        this.badge = badge;
    }

}
