package it.myalert.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.myalert.adapterConverter.UserAdapter;
import it.myalert.entity.User;
import it.myalert.repository.UserRepository;
import it.myalert.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends UserAdapter implements UserService  {
	
	
	@Autowired
	private UserRepository userRepository;

	
	@Override
	public List<User> getAll() {
		List<User> users = this.userRepository.findAll();
		return users;
	}


	@Override
	public User addUser(User user) {
		
		return this.userRepository.save(user);
	}

	

}
