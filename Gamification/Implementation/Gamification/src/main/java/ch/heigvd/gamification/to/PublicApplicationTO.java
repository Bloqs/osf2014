package ch.heigvd.gamification.to;

import ch.heigvd.gamification.services.to.PlayersTOServiceLocal;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is a Transfer Object, which will be used to send data to the client.
 * Instances of this class will be created from JPA entities, but will not
 * expose all attributes. The class must have an empty constructor, as well as
 * getters and setters for properties (this is required for the JAXB marshalling
 * to work properly). That is also why we have the @XmlRootElement annotation at
 * the class level.
 *
 * @author Khaled Basbous
 */

@XmlRootElement
public class PublicApplicationTO {

    private String apiKey;
    private String name;
    private String description;
    private Collection<PublicPlayerTO> players;
    
    @EJB PlayersTOServiceLocal playersTOService;
    
    public PublicApplicationTO() {
    }

    public PublicApplicationTO(String apiKey, String name, String description, List<PublicPlayerTO> players) {
        this.apiKey = apiKey;
        this.name = name;
        this.description = description;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Collection<PublicPlayerTO> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<PublicPlayerTO> players) {
        this.players = players;
    }
    
}
