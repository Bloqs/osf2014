/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.to;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Khaled
 */
@XmlRootElement
public class PrivateApplicationTO extends PublicApplicationTO {
    String apiSecret;

    public PrivateApplicationTO(){
        
    }
    
    public PrivateApplicationTO(String apiKey, String name, String description, String apiSecret, List<PublicPlayerTO> players) {
        super(apiKey, name, description, players);
        this.apiSecret = apiSecret;
    }
    
    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }
    
}
