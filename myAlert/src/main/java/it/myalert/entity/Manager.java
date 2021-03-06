// Generated with g9.

package it.myalert.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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

@Entity(name="manager")
public class Manager implements Serializable {

    /** Primary key. */
    protected static final String PK = "idManager";


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private Integer idManager;
    @Column(name="StartDate_task", nullable=false)
    private Timestamp startDateTask;
    @Column(name="EndDate_task")
    private Timestamp endDateTask;
    @OneToMany(mappedBy="manager")
    private Set<Agent> agent;
    @OneToMany(mappedBy="manager")
    private Set<Assign> assign;
    @ManyToOne(optional=false, cascade= {CascadeType.PERSIST})
    @JoinColumn(name="idUser_FK", nullable=false)
    private User user;
    @OneToMany(mappedBy="manager")
    private Set<Type> type;

    /** Default constructor. */
    public Manager() {
        super();
    }

    /**
     * Access method for idManager.
     *
     * @return the current value of idManager
     */
    public Integer getIdManager() {
        return idManager;
    }

    /**
     * Setter method for idManager.
     *
     * @param aIdManager the new value for idManager
     */
    public void setIdManager(Integer aIdManager) {
        idManager = aIdManager;
    }

    /**
     * Access method for startDateTask.
     *
     * @return the current value of startDateTask
     */
    public Timestamp getStartDateTask() {
        return startDateTask;
    }

    /**
     * Setter method for startDateTask.
     *
     * @param aStartDateTask the new value for startDateTask
     */
    public void setStartDateTask(Timestamp aStartDateTask) {
        startDateTask = aStartDateTask;
    }

    /**
     * Access method for endDateTask.
     *
     * @return the current value of endDateTask
     */
    public Timestamp getEndDateTask() {
        return endDateTask;
    }

    /**
     * Setter method for endDateTask.
     *
     * @param aEndDateTask the new value for endDateTask
     */
    public void setEndDateTask(Timestamp aEndDateTask) {
        endDateTask = aEndDateTask;
    }

    /**
     * Access method for agent.
     *
     * @return the current value of agent
     */
    public Set<Agent> getAgent() {
        return agent;
    }

    /**
     * Setter method for agent.
     *
     * @param aAgent the new value for agent
     */
    public void setAgent(Set<Agent> aAgent) {
        agent = aAgent;
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
     * Access method for type.
     *
     * @return the current value of type
     */
    public Set<Type> getType() {
        return type;
    }

    /**
     * Setter method for type.
     *
     * @param aType the new value for type
     */
    public void setType(Set<Type> aType) {
        type = aType;
    }

    /**
     * Compares the key for this instance with another Manager.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Manager and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Manager)) {
            return false;
        }
        Manager that = (Manager) other;
        if (this.getIdManager() != that.getIdManager()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Manager.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Manager)) return false;
        return this.equalKeys(other) && ((Manager)other).equalKeys(this);
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
        i = getIdManager();
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
        StringBuffer sb = new StringBuffer("[Manager |");
        sb.append(" idManager=").append(getIdManager());
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
        ret.put("idManager", Integer.valueOf(getIdManager()));
        return ret;
    }

}
