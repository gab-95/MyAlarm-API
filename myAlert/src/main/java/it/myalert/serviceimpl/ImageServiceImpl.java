package it.myalert.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.myalert.adapterConverter.ImageAdapter;
import it.myalert.entity.Citizen;
import it.myalert.entity.Image;
import it.myalert.entity.Intervention;
import it.myalert.entity.Type;
import it.myalert.exeption.ImageExeption;
import it.myalert.exeption.InterventionExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.TypeExeption;
import it.myalert.exeption.UserExeption;
import it.myalert.repository.CitizenRepository;
import it.myalert.repository.ImageRepository;
import it.myalert.service.ImageService;

@Service
@Transactional(rollbackOn = ImageExeption.class)
public class ImageServiceImpl extends ImageAdapter implements ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public List<Image> getAll() {
		List<Image> list = this.imageRepository.findAll();
		return list;
	}

	@Override
	public List<Image> getAllImageByIdIntervention(int idIntervention) throws InterventionExeption {
		return this.imageRepository.findByIntervention_idIntervention(idIntervention);
	}
	
	@Override
	public List<Image> findByIntervention_idInterventionAndUser_idUser(int idIntervention, int idUser) throws InterventionExeption,UserException {
		return imageRepository.findByIntervention_idInterventionAndUser_idUser(idIntervention, idUser);
	}
	
	@Override
	public Image addImage(Image image) throws UserExeption {
		image.setIdImage(null);
		return this.imageRepository.save(image);
	}
	
	@Override
	public Boolean deleteImage(int idImage) throws ImageExeption {
		
		Image image = this.imageRepository.findById(idImage).orElseThrow(()-> new ImageExeption("ERROR: No type found with id:"+ idImage));
		if(image != null) {
			this.imageRepository.delete(image);
			return true;
		}
		return null;
	}

}
