/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.to;

/**
 *
 * @author Jean-Luc
 */
public class PublicEventTO {
    
    private int id;
    private String type;
    private PublicApplicationTO applicationTO;
    private PublicPlayerTO playerTO;
    
    public PublicEventTO() {}
    public PublicEventTO(String type, PublicApplicationTO app, PublicPlayerTO player)
    {
        this.type = type;
        this.applicationTO = app;
        this.playerTO = player;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setApplicationTO(PublicApplicationTO applicationTO) {
        this.applicationTO = applicationTO;
    }

    public void setPlayerTO(PublicPlayerTO playerTO) {
        this.playerTO = playerTO;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public PublicApplicationTO getApplicationTO() {
        return applicationTO;
    }
}
