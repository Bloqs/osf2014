/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jean-Luc
 */
@XmlRootElement
public class PublicRuleTO {
    private int id;
    private String onEventType;
    private int numberOfPoints;
    private PublicApplicationTO applicationTO;
    
    public PublicRuleTO () {}
    
    public PublicRuleTO (String event, int points, PublicApplicationTO app)
    {
        this.onEventType = event;
        this.numberOfPoints = points;
        this.applicationTO = app;
    }
    
        public void setId(int id) {
        this.id = id;
    }

    public void setOnEventType(String onEventType) {
        this.onEventType = onEventType;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public void setApplicationTO(PublicApplicationTO applicationTO) {
        this.applicationTO = applicationTO;
    }

    public int getId() {
        return id;
    }

    public String getOnEventType() {
        return onEventType;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public PublicApplicationTO getApplicationTO() {
        return applicationTO;
    }
}
