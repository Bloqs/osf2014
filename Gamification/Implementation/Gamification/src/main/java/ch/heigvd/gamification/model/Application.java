package ch.heigvd.gamification.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import ch.heigvd.gamification.model.Player;
import java.util.ArrayList;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * This class is application JPA entity.
 *
 * @author Khaled Basbous
 */
@NamedQueries(
        @NamedQuery(
                name = "findAllApplications",
                query = "SELECT a FROM Application a"
        )
)

@Entity
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "application")
    private Long apiKey;

    private String name;
    private String description;
    private String apiSecret;
    
    @OneToMany(mappedBy="application", fetch = FetchType.LAZY)
    private Collection<Player> players;
    @OneToMany(mappedBy="rule", fetch = FetchType.LAZY)
    private Collection<Rule> rules;
    @OneToMany(mappedBy="event", fetch = FetchType.LAZY)
    private Collection<Event> events;
    
    public Application() {
        name = "UNDEF";
        description = "UNDEF";
        apiSecret = "UNDEF";
        players = null;
    }

    public Application(Application applicationData) {
        this.name = applicationData.name;
        this.description = applicationData.description;
        this.apiSecret = applicationData.apiSecret;
    }

    public Long getApiKey() {
        return apiKey;
    }

    public void setApiKey(Long apiKey) {
        this.apiKey = apiKey;
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

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public Collection<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<Player> players) {
        this.players = players;
    }
 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apiKey != null ? apiKey.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the apiKey fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.apiKey == null && other.apiKey != null) || (this.apiKey != null && !this.apiKey.equals(other.apiKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.heigvd.gamification.model.Application[ id=" + this.apiKey + " ]";
    }

}
