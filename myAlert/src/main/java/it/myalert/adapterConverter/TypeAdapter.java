package it.myalert.adapterConverter;

import org.springframework.beans.factory.annotation.Autowired;

import it.myalert.DTO.TypeDTO;
import it.myalert.entity.Type;

public class TypeAdapter implements Converter<TypeDTO, Type> {

	@Autowired
	ManagerAdapter managerAdapter;
	@Override
	public TypeDTO convertToDTO(Type type) {
		
		TypeDTO typeDTO = new TypeDTO();
		typeDTO.setIdType(type.getIdType());
		if(type.getManager() != null) {			
			typeDTO.setManagerDTO(managerAdapter.convertToDTO(type.getManager()));
		}
		typeDTO.setName(type.getName());
		return typeDTO;
	}

	@Override
	public Type convertToEntity(TypeDTO typeDTO) {

		Type type = new Type();
		type.setIdType(typeDTO.getIdType());
		if(typeDTO.getManagerDTO() != null) {			
			type.setManager(managerAdapter.convertToEntity(typeDTO.getManagerDTO()));
		}
		type.setName(typeDTO.getName());
		return type;
	}

}
