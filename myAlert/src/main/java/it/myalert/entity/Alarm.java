// Generated with g9.

package it.myalert.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity(name="alarm")
public class Alarm implements Serializable {

    /** Primary key. */
    protected static final String PK = "idAlarm";



    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private Integer idAlarm;
    @Column(name="AlarmDate")
    private Timestamp alarmDate;
    @ManyToOne(optional=false)
    @JoinColumn(name="idCitizen", nullable=false)
    private Citizen citizen;
    @ManyToOne(optional=false)
    @JoinColumn(name="idIntervention", nullable=false)
    private Intervention intervention;
    @Column(name="Title", length=200)
    private String title;
    @Column(name="Description", length=16777215)
    private String description;

    /** Default constructor. */
    public Alarm() {
        super();
    }

    /**
     * Access method for idAlarm.
     *
     * @return the current value of idAlarm
     */
    public Integer getIdAlarm() {
        return idAlarm;
    }

    /**
     * Setter method for idAlarm.
     *
     * @param aIdAlarm the new value for idAlarm
     */
    public void setIdAlarm(Integer aIdAlarm) {
        idAlarm = aIdAlarm;
    }

    /**
     * Access method for alarmDate.
     *
     * @return the current value of alarmDate
     */
    public Timestamp getAlarmDate() {
        return alarmDate;
    }

    /**
     * Setter method for alarmDate.
     *
     * @param aAlarmDate the new value for alarmDate
     */
    public void setAlarmDate(Timestamp aAlarmDate) {
        alarmDate = aAlarmDate;
    }

    /**
     * Access method for citizen.
     *
     * @return the current value of citizen
     */
    public Citizen getCitizen() {
        return citizen;
    }

    /**
     * Setter method for citizen.
     *
     * @param aCitizen the new value for citizen
     */
    public void setCitizen(Citizen aCitizen) {
        citizen = aCitizen;
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
     * Access method for title.
     *
     * @return the current value of title
     */
    public String getTitle() {
		return title;
	}

    /**
     * Setter method for title.
     *
     * @param aTitle the new value for title
     */
	public void setTitle(String aTitle) {
		this.title = aTitle;
	}
	
	/**
     * Access method for description.
     *
     * @return the current value of description
     */
	public String getDescription() {
		return description;
	}

	/**
     * Setter method for description.
     *
     * @param aDescription the new value for description
     */
	public void setDescription(String aDescription) {
		this.description = aDescription;
	}

    /**
     * Compares the key for this instance with another Alarm.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Alarm and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Alarm)) {
            return false;
        }
        Alarm that = (Alarm) other;
        if (this.getIdAlarm() != that.getIdAlarm()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Alarm.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Alarm)) return false;
        return this.equalKeys(other) && ((Alarm)other).equalKeys(this);
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
        i = getIdAlarm();
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
        StringBuffer sb = new StringBuffer("[Alarm |");
        sb.append(" idAlarm=").append(getIdAlarm());
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
        ret.put("idAlarm", Integer.valueOf(getIdAlarm()));
        return ret;
    }

}
