package ch.heigvd.gamification.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * This class is application JPA entity.
 *
 * @author Khaled Basbous
 */
@NamedQueries({
    @NamedQuery(
            name = "findAllPlayers",
            query = "SELECT p FROM Player p"
    ),
    @NamedQuery(
            name = "findLeaderPlayers",
            query = "SELECT p FROM Player p WHERE p.application.apiKey = :apiKey ORDER BY p.numberOfPoints DESC"
    )

})

@Entity
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "player")
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    private Integer numberOfPoints;

    @ManyToOne
    private Application application;
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Collection<Event> events;
    @OneToMany
    private Collection<Badge> badges;

    public Player() {
        firstName = "UNDEF";
        lastName = "UNDEF";
        email = "UNDEF";
        numberOfPoints = -1;
        application = null;
    }

    public Player(Player playerData) {
        this.firstName = playerData.firstName;
        this.lastName = playerData.lastName;
        this.email = playerData.email;
        this.numberOfPoints = playerData.numberOfPoints;
        this.application = playerData.application;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    public Collection<Badge> getBadges() {
        return badges;
    }

    public void setBadges(Collection<Badge> badges) {
        this.badges = badges;
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
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.heigvd.gamification.model.Player[ id=" + this.id + " ]";
    }

}
