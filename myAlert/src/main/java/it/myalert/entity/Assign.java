// Generated with g9.

package it.myalert.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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

@Entity(name="assign")
public class Assign implements Serializable {

    /** Primary key. */
    protected static final String PK = "idAssign";



    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int idAssign;
    @Column(name="Confirm", nullable=false, precision=3)
    private short confirm;
    @Column(name="HasWritten", nullable=false, precision=3)
    private short hasWritten;
    @Column(name="Start_validate")
    private Timestamp startValidate;
    @Column(name="End_validate")
    private Timestamp endValidate;
    @ManyToOne(optional=false)
    @JoinColumn(name="idAgent", nullable=false)
    private Agent agent;
    @ManyToOne(optional=false)
    @JoinColumn(name="idIntervation", nullable=false)
    private Intervention intervention;
    @ManyToOne(optional=false)
    @JoinColumn(name="idManager", nullable=false)
    private Manager manager;

    /** Default constructor. */
    public Assign() {
        super();
    }

    /**
     * Access method for idAssign.
     *
     * @return the current value of idAssign
     */
    public int getIdAssign() {
        return idAssign;
    }

    /**
     * Setter method for idAssign.
     *
     * @param aIdAssign the new value for idAssign
     */
    public void setIdAssign(int aIdAssign) {
        idAssign = aIdAssign;
    }

    /**
     * Access method for confirm.
     *
     * @return the current value of confirm
     */
    public short getConfirm() {
        return confirm;
    }

    /**
     * Setter method for confirm.
     *
     * @param aConfirm the new value for confirm
     */
    public void setConfirm(short aConfirm) {
        confirm = aConfirm;
    }

    /**
     * Access method for hasWritten.
     *
     * @return the current value of hasWritten
     */
    public short getHasWritten() {
        return hasWritten;
    }

    /**
     * Setter method for hasWritten.
     *
     * @param aHasWritten the new value for hasWritten
     */
    public void setHasWritten(short aHasWritten) {
        hasWritten = aHasWritten;
    }

    /**
     * Access method for startValidate.
     *
     * @return the current value of startValidate
     */
    public Timestamp getStartValidate() {
        return startValidate;
    }

    /**
     * Setter method for startValidate.
     *
     * @param aStartValidate the new value for startValidate
     */
    public void setStartValidate(Timestamp aStartValidate) {
        startValidate = aStartValidate;
    }

    /**
     * Access method for endValidate.
     *
     * @return the current value of endValidate
     */
    public Timestamp getEndValidate() {
        return endValidate;
    }

    /**
     * Setter method for endValidate.
     *
     * @param aEndValidate the new value for endValidate
     */
    public void setEndValidate(Timestamp aEndValidate) {
        endValidate = aEndValidate;
    }

    /**
     * Access method for agent.
     *
     * @return the current value of agent
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Setter method for agent.
     *
     * @param aAgent the new value for agent
     */
    public void setAgent(Agent aAgent) {
        agent = aAgent;
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
     * Access method for manager.
     *
     * @return the current value of manager
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * Setter method for manager.
     *
     * @param aManager the new value for manager
     */
    public void setManager(Manager aManager) {
        manager = aManager;
    }

    /**
     * Compares the key for this instance with another Assign.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Assign and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Assign)) {
            return false;
        }
        Assign that = (Assign) other;
        if (this.getIdAssign() != that.getIdAssign()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Assign.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Assign)) return false;
        return this.equalKeys(other) && ((Assign)other).equalKeys(this);
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
        i = getIdAssign();
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
        StringBuffer sb = new StringBuffer("[Assign |");
        sb.append(" idAssign=").append(getIdAssign());
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
        ret.put("idAssign", Integer.valueOf(getIdAssign()));
        return ret;
    }

}
