package ch.heigvd.gamification.to;

import java.util.List;
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
public class PublicLeaderBoardTO {

    private PublicApplicationTO application;
    private List<PublicPlayerTO> ranking;
        
    public PublicLeaderBoardTO() {
    }

    public PublicLeaderBoardTO(PublicApplicationTO application, List<PublicPlayerTO> players) {
        this.application = application;
        this.ranking = players;
    }

    public PublicApplicationTO getApplication() {
        return application;
    }

    public void setApplication(PublicApplicationTO application) {
        this.application = application;
    }

    public List<PublicPlayerTO> getRanking() {
        return ranking;
    }

    public void setRanking(List<PublicPlayerTO> ranking) {
        this.ranking = ranking;
    }

}
