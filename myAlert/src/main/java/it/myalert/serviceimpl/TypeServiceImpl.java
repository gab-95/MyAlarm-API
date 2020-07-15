package it.myalert.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.myalert.adapterConverter.TypeAdapter;
import it.myalert.entity.Type;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.TypeExeption;
import it.myalert.repository.TypeRepository;
import it.myalert.service.TypeService;

@Service
@Transactional(rollbackOn = ManagerExeption.class)
public class TypeServiceImpl extends TypeAdapter implements TypeService {

	
	@Autowired
	private TypeRepository typeRepository;
	
	@Override
	public List<Type> getAll() {
		
		return this.typeRepository.findAll();
	}
	
	@Override
	public Type getTypeById(int idType) throws TypeExeption {
		return this.typeRepository.findById(idType).orElseThrow(()-> new TypeExeption("ERROR: No type found with id:"+ idType));
		
	}
	
	@Override
	public Type addType(Type type) throws ManagerExeption {
		type.setIdType(null);
		return this.typeRepository.save(type);
	}

	@Override
	public Type updateType(Type type) throws TypeExeption {
		
		return this.typeRepository.save(type);
	}

	@Override
	public Boolean deleteType(int idType) throws TypeExeption {
		
		Type type = this.typeRepository.findById(idType).orElseThrow(()-> new TypeExeption("ERROR: No type found with id:"+ idType));
		if(type != null) {
			this.typeRepository.delete(type);
			return true;
		}
		return null;
	}

	

	

}
