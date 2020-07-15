// Generated with g9.

package it.myalert.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity(name="intervention")
public class Intervention implements Serializable {

    /** Primary key. */
    protected static final String PK = "idIntervention";



    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private Integer idIntervention;
    @Column(name="Lat", nullable=false, length=15)
    private String lat;
    @Column(name="Lon", nullable=false, length=15)
    private String lon;
    @Column(name="Address", nullable=false, length=45)
    private String address;
    @Column(name="City", nullable=false, length=45)
    private String city;
    @Column(name="StartDate")
    private Timestamp startDate;
    @Column(name="EndDate")
    private Timestamp endDate;
    @Column(name="ShortReport", length=16777215)
    private String shortReport;
    @Column(name="DetailedReport")
    private String detailedReport;
    @Column(name="Status", nullable=false, length=45)
    private String status;
    @ManyToOne(optional=false)
    @JoinColumn(name="idType", nullable=false)
    private Type type;
    @OneToMany(mappedBy="intervention")
    private Set<Assign> assign;
    @OneToMany(mappedBy="intervention")
    private Set<Alarm> alarm;
    @OneToMany(mappedBy="intervention")
    private Set<Image> image;

    /** Default constructor. */
    public Intervention() {
        super();
    }

    /**
     * Access method for idIntervention.
     *
     * @return the current value of idIntervention
     */
    public Integer getIdIntervention() {
        return idIntervention;
    }

    /**
     * Setter method for idIntervention.
     *
     * @param aIdIntervation the new value for idIntervention
     */
    public void setIdIntervention(Integer aIdIntervention) {
        idIntervention = aIdIntervention;
    }

    /**
     * Access method for lat.
     *
     * @return the current value of lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * Setter method for lat.
     *
     * @param aLat the new value for lat
     */
    public void setLat(String aLat) {
        lat = aLat;
    }

    /**
     * Access method for lon.
     *
     * @return the current value of lon
     */
    public String getLon() {
        return lon;
    }

    /**
     * Setter method for lon.
     *
     * @param aLong_ the new value for lon
     */
    public void setLon(String aLon) {
        lon = aLon;
    }

    /**
     * Access method for address.
     *
     * @return the current value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for address.
     *
     * @param aAddress the new value for address
     */
    public void setAddress(String aAddress) {
        address = aAddress;
    }

    /**
     * Access method for city.
     *
     * @return the current value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter method for city.
     *
     * @param aCity the new value for city
     */
    public void setCity(String aCity) {
        city = aCity;
    }

    /**
     * Access method for startDate.
     *
     * @return the current value of startDate
     */
    public Timestamp getStartDate() {
        return startDate;
    }

    /**
     * Setter method for startDate.
     *
     * @param aStartDate the new value for startDate
     */
    public void setStartDate(Timestamp aStartDate) {
        startDate = aStartDate;
    }

    /**
     * Access method for endDate.
     *
     * @return the current value of endDate
     */
    public Timestamp getEndDate() {
        return endDate;
    }

    /**
     * Setter method for endDate.
     *
     * @param aEndDate the new value for endDate
     */
    public void setEndDate(Timestamp aEndDate) {
        endDate = aEndDate;
    }

    /**
     * Access method for shortReport.
     *
     * @return the current value of shortReport
     */
    public String getShortReport() {
        return shortReport;
    }

    /**
     * Setter method for shortReport.
     *
     * @param aShortReport the new value for shortReport
     */
    public void setShortReport(String aShortReport) {
        shortReport = aShortReport;
    }

    /**
     * Access method for detailedReport.
     *
     * @return the current value of detailedReport
     */
    public String getDetailedReport() {
        return detailedReport;
    }

    /**
     * Setter method for detailedReport.
     *
     * @param aDetailedReport the new value for detailedReport
     */
    public void setDetailedReport(String aDetailedReport) {
        detailedReport = aDetailedReport;
    }

    /**
     * Access method for status.
     *
     * @return the current value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for status.
     *
     * @param aStatus the new value for status
     */
    public void setStatus(String aStatus) {
        status = aStatus;
    }

    /**
     * Access method for type.
     *
     * @return the current value of type
     */
    public Type getType() {
        return type;
    }

    /**
     * Setter method for type.
     *
     * @param aType the new value for type
     */
    public void setType(Type aType) {
        type = aType;
    }

    /**
     * Access method for assign.
     *
     * @return the current value of assign
     */
    public Set<Assign> getAssign() {
        return assign;
    }

    /**
     * Setter method for assign.
     *
     * @param aAssign the new value for assign
     */
    public void setAssign(Set<Assign> aAssign) {
        assign = aAssign;
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
     * Access method for image.
     *
     * @return the current value of image
     */
    public Set<Image> getImage() {
        return image;
    }

    /**
     * Setter method for image.
     *
     * @param aImage the new value for image
     */
    public void setImage(Set<Image> aImage) {
        image = aImage;
    }

    /**
     * Compares the key for this instance with another Intervention.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Intervention and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Intervention)) {
            return false;
        }
        Intervention that = (Intervention) other;
        if (this.getIdIntervention() != that.getIdIntervention()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Intervention.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Intervention)) return false;
        return this.equalKeys(other) && ((Intervention)other).equalKeys(this);
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
        i = getIdIntervention();
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
        StringBuffer sb = new StringBuffer("[Intervention |");
        sb.append(" idIntervation=").append(getIdIntervention());
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
        ret.put("idIntervation", Integer.valueOf(getIdIntervention()));
        return ret;
    }

}
