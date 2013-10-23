package ch.heigvd.gamification.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String appName;
    private String appDescription;

    public Application() {
        appName = "UNDEF";
        appDescription = "UNDEF";
    }

    public Application(Application applicationData) {
        this.appName = applicationData.appName;
        this.appDescription = applicationData.appDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String name) {
        this.appName = name;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String description) {
        this.appDescription = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.heigvd.gamification.model.Application[ id=" + id + " ]";
    }

}
