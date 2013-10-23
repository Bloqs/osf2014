package ch.heigvd.gamification.to;

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

    private long applicationId;

    private String name;

    private String description;

    public PublicApplicationTO() {
    }

    public PublicApplicationTO(long applicationId, String name, String description) {
        this.applicationId = applicationId;
        this.name = name;
        this.description = description;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
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

}
