package it.myalert.adapterConverter;

import org.springframework.beans.factory.annotation.Autowired;

import it.myalert.DTO.ImageDTO;
import it.myalert.entity.Image;

public class ImageAdapter implements Converter<ImageDTO, Image>{

	@Autowired
	private UserAdapter userAdapter;
	@Autowired
	private InterventionAdapter interventionAdapter;
	
	@Override
	public ImageDTO convertToDTO(Image image) {

		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setIdImage(image.getIdImage());
		imageDTO.setUrl(image.getUrl());
		imageDTO.setLat(image.getLat());
		imageDTO.setLon(image.getLon());
		imageDTO.setUser(userAdapter.convertToDTO(image.getUser()));
		imageDTO.setIntervention(interventionAdapter.convertToDTO(image.getIntervention()));
		return imageDTO;
	}

	@Override
	public Image convertToEntity(ImageDTO imageDTO) {

		Image image = new Image();
		image.setIdImage(imageDTO.getIdImage());
		image.setUrl(imageDTO.getUrl());
		image.setLat(imageDTO.getLat());
		image.setLon(imageDTO.getLon());
		image.setUser(userAdapter.convertToEntity(imageDTO.getUser()));
		image.setIntervention(interventionAdapter.convertToEntity(imageDTO.getIntervention()));
		return image;
	}

}
