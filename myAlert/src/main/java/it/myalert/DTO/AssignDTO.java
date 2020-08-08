package it.myalert.DTO;

import java.util.Date;

public class AssignDTO {

	private int idAssign;
	private ManagerDTO manager;
	private AgentDTO agent;
	private Boolean confirm;
	private Boolean hasWritten;
	private Date startValidate;
	private Date endValidate;
	private InterventionDTO intervention;
	public int getIdAssign() {
		return idAssign;
	}
	public void setIdAssign(int idAssign) {
		this.idAssign = idAssign;
	}
	public ManagerDTO getManager() {
		return manager;
	}
	public void setManager(ManagerDTO manager) {
		this.manager = manager;
	}
	public AgentDTO getAgent() {
		return agent;
	}
	public void setAgent(AgentDTO agent) {
		this.agent = agent;
	}
	public Boolean getConfirm() {
		return confirm;
	}
	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}
	public Boolean getHasWritten() {
		return hasWritten;
	}
	public void setHasWritten(Boolean hasWritten) {
		this.hasWritten = hasWritten;
	}
	public Date getStartValidate() {
		return startValidate;
	}
	public void setStartValidate(Date startValidate) {
		this.startValidate = startValidate;
	}
	public Date getEndValidate() {
		return endValidate;
	}
	public void setEndValidate(Date endValidate) {
		this.endValidate = endValidate;
	}
	public InterventionDTO getIntervention() {
		return intervention;
	}
	public void setIntervention(InterventionDTO intervention) {
		this.intervention = intervention;
	}
	
	
}
