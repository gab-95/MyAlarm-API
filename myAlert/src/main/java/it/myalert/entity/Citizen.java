// Generated with g9.

package it.myalert.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity(name="citizen")
public class Citizen implements Serializable {

    /** Primary key. */
    protected static final String PK = "idCitizen";

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private Integer idCitizen;
    @Column(name="Lat")
    private Double lat;
    @Column(name="Lon")
    private Double lon;
    @OneToMany(mappedBy="citizen")
    private Set<Alarm> alarm;
    @ManyToOne(optional=false, cascade= {CascadeType.PERSIST})
    @JoinColumn(name="idUser_FK", nullable=false)
    private User user;

    /** Default constructor. */
    public Citizen() {
        super();
    }

    /**
     * Access method for idCitizen.
     *
     * @return the current value of idCitizen
     */
    public Integer getIdCitizen() {
        return idCitizen;
    }

    /**
     * Setter method for idCitizen.
     *
     * @param aIdCitizen the new value for idCitizen
     */
    public void setIdCitizen(Integer aIdCitizen) {
        idCitizen = aIdCitizen;
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
     * @param aLon the new value for lon
     */
    public void setLon(Double aLon) {
        lon = aLon;
    }

    /**
     * Access method for alarm.
     *
     * @return the current value of alarm
     */
    public Set<Alarm> getAlarm() {
        return alarm;
    }

    /**
     * Setter method for alarm.
     *
     * @param aAlarm the new value for alarm
     */
    public void setAlarm(Set<Alarm> aAlarm) {
        alarm = aAlarm;
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
     * Compares the key for this instance with another Citizen.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Citizen and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Citizen)) {
            return false;
        }
        Citizen that = (Citizen) other;
        if (this.getIdCitizen() != that.getIdCitizen()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Citizen.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Citizen)) return false;
        return this.equalKeys(other) && ((Citizen)other).equalKeys(this);
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
        i = getIdCitizen();
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
        StringBuffer sb = new StringBuffer("[Citizen |");
        sb.append(" idCitizen=").append(getIdCitizen());
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
        ret.put("idCitizen", Integer.valueOf(getIdCitizen()));
        return ret;
    }

}