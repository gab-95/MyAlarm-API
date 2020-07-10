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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="type", indexes={@Index(name="type_Name_IX", columnList="Name", unique=true)})
public class Type implements Serializable {

    /** Primary key. */
    protected static final String PK = "idType";

    

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private Integer idType;
    @Column(name="Name", unique=true, nullable=false, length=45)
    private String name;
    @OneToMany(mappedBy="type")
    private Set<Intervention> intervention;
    @ManyToOne(optional=false)
    @JoinColumn(name="idManager", nullable=false)
    private Manager manager;

    /** Default constructor. */
    public Type() {
        super();
    }

    /**
     * Access method for idType.
     *
     * @return the current value of idType
     */
    public Integer getIdType() {
        return idType;
    }

    /**
     * Setter method for idType.
     *
     * @param aIdType the new value for idType
     */
    public void setIdType(Integer aIdType) {
        idType = aIdType;
    }

    /**
     * Access method for name.
     *
     * @return the current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param aName the new value for name
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Access method for intervention.
     *
     * @return the current value of intervention
     */
    public Set<Intervention> getIntervention() {
        return intervention;
    }

    /**
     * Setter method for intervention.
     *
     * @param aIntervention the new value for intervention
     */
    public void setIntervention(Set<Intervention> aIntervention) {
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
     * Compares the key for this instance with another Type.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Type and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Type)) {
            return false;
        }
        Type that = (Type) other;
        if (this.getIdType() != that.getIdType()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Type.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Type)) return false;
        return this.equalKeys(other) && ((Type)other).equalKeys(this);
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
        i = getIdType();
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
        StringBuffer sb = new StringBuffer("[Type |");
        sb.append(" idType=").append(getIdType());
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
        ret.put("idType", Integer.valueOf(getIdType()));
        return ret;
    }

}
