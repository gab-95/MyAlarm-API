package it.myalert.adapterConverter;

import java.sql.Timestamp;

import javax.el.TypeConverter;

import org.springframework.beans.factory.annotation.Autowired;

import ch.qos.logback.core.joran.conditional.IfAction;
import it.myalert.DTO.InterventionDTO;
import it.myalert.entity.Intervention;

public class InterventionAdapter implements Converter<InterventionDTO, Intervention>{

	@Autowired
	private TypeAdapter typeConverter ;
	
	@Override
	public InterventionDTO convertToDTO(Intervention intervention) {

		InterventionDTO interventionDTO = new InterventionDTO();
		interventionDTO.setIdIntervention(intervention.getIdIntervention());
		interventionDTO.setType(typeConverter.convertToDTO(intervention.getType()));
		interventionDTO.setLat(intervention.getLat());
		interventionDTO.setLon(intervention.getLon());
		interventionDTO.setAddress(intervention.getAddress());
		interventionDTO.setCity(intervention.getCity());
		interventionDTO.setStartDate(intervention.getStartDate());
		interventionDTO.setEndDate(intervention.getEndDate());
		interventionDTO.setShortReport(intervention.getShortReport());
		interventionDTO.setDetailedReport(intervention.getDetailedReport());
		interventionDTO.setStatus(intervention.getStatus());
		
		return interventionDTO;
	}

	@Override
	public Intervention convertToEntity(InterventionDTO interventionDTO) {

		Intervention intervention = new Intervention();
		intervention.setIdIntervention(interventionDTO.getIdIntervention());
		intervention.setType(typeConverter.convertToEntity(interventionDTO.getType()));
		intervention.setLat(interventionDTO.getLat());
		intervention.setLon(interventionDTO.getLon());
		intervention.setAddress(interventionDTO.getAddress());
		intervention.setCity(interventionDTO.getCity());
		if(interventionDTO.getStartDate() != null) {
			intervention.setStartDate(new Timestamp(interventionDTO.getStartDate().getTime()));
		}
		if(interventionDTO.getEndDate() != null) {
			intervention.setEndDate(new Timestamp(interventionDTO.getEndDate().getTime()));
		}
		intervention.setShortReport(interventionDTO.getShortReport());
		intervention.setDetailedReport(interventionDTO.getDetailedReport());
		intervention.setStatus(interventionDTO.getStatus());
		
		return intervention;
	}

}
