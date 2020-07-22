package it.myalert.service;

import java.util.List;

import org.omg.CORBA.UserException;

import it.myalert.DTO.ImageDTO;
import it.myalert.adapterConverter.Converter;
import it.myalert.entity.Image;
import it.myalert.entity.Type;
import it.myalert.exeption.ImageExeption;
import it.myalert.exeption.InterventionExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.TypeExeption;
import it.myalert.exeption.UserExeption;

public interface ImageService extends Converter<ImageDTO, Image> {
	
	public List<Image> getAll();
	public List<Image> getAllImageByIdIntervention(int idIntervention) throws InterventionExeption;
	public List<Image> findByIntervention_idInterventionAndUser_idUser(int idIntervention, int idUser) throws InterventionExeption,UserException;
	public Image addImage(Image image) throws UserExeption;
	public Boolean deleteImage(int idImage) throws ImageExeption;

}
