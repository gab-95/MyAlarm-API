// Generated with g9.

package it.myalert.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity(name="image")
public class Image implements Serializable {

    /** Primary key. */
    protected static final String PK = "idImage";


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Integer idImage;
    @Column(name="Url", nullable=false, length=16777215)
    private String url;
    @Column(name="Lat")
    private Double lat;
    @Column(name="Lon")
    private Double lon;
    @ManyToOne(optional=false)
    @JoinColumn(name="idIntervention", nullable=false)
    private Intervention intervention;
    @ManyToOne(optional=false)
    @JoinColumn(name="idUser", nullable=false)
    private User user;

    /** Default constructor. */
    public Image() {
        super();
    }

    /**
     * Access method for idImage.
     *
     * @return the current value of idImage
     */
    public Integer getIdImage() {
        return idImage;
    }

    /**
     * Setter method for idImage.
     *
     * @param aIdImage the new value for idImage
     */
    public void setIdImage(Integer aIdImage) {
        idImage = aIdImage;
    }

    /**
     * Access method for url.
     *
     * @return the current value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for url.
     *
     * @param aUrl the new value for url
     */
    public void setUrl(String aUrl) {
        url = aUrl;
    }

    /**
     * Access method for lat.
     *
     * @return the current value of lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * Setter method for lat.
     *
     * @param aLat the new value for lat
     */
    public void setLat(Double aLat) {
        lat = aLat;
    }

    /**
     * Access method for lon.
     *
     * @return the current value of lon
     */
    public Double getLon() {
        return lon;
    }

    /**
     * Setter method for lon.
     *
     * @param aLong_ the new value for lon
     */
    public void setLon(Double aLon) {
        lon = aLon;
    }

    /**
     * Access method for intervention.
     *
     * @return the current value of intervention
     */
    public Intervention getIntervention() {
        return intervention;
    }

    /**
     * Setter method for intervention.
     *
     * @param aIntervention the new value for intervention
     */
    public void setIntervention(Intervention aIntervention) {
        intervention = aIntervention;
    }

    /**
     * Access method for user.
     *
     * @return the current value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter method for user.
     *
     * @param aUser the new value for user
     */
    public void setUser(User aUser) {
        user = aUser;
    }

    /**
     * Compares the key for this instance with another Image.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Image and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Image)) {
            return false;
        }
        Image that = (Image) other;
        if (this.getIdImage() != that.getIdImage()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Image.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Image)) return false;
        return this.equalKeys(other) && ((Image)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getIdImage();
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Image |");
        sb.append(" idImage=").append(getIdImage());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("idImage", Integer.valueOf(getIdImage()));
        return ret;
    }

}
