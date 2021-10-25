package userpack.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import userpack.dto.UserDto;
import userpack.entity.User;
import userpack.repo.UserRepository;
import userpack.util.UserEntityDtoUtil;
import userpack.exception.UserException;

@Component
public class UserServiceImpl {

	@Autowired
	private UserRepository userrepo;

	//private UserDto userdto;

	// get all users
	public List<UserDto> getAll() {
		return userrepo.findAll().stream().map(UserEntityDtoUtil::entityToDto).collect(Collectors.toList());

	}

	// view an employee
	
	 public UserDto getById(Long id) {
	  
	  Optional<UserDto> optionaluser =
	  Optional.ofNullable(userrepo.findById(id).map(UserEntityDtoUtil::entityToDto)
	  .orElse(null)); 
	  return optionaluser.orElseThrow(() -> new UserException(+id+"Not Found"));
	  
	  
	  }
	 

	// create an user
	public UserDto save(UserDto u) {

		
		return UserEntityDtoUtil.entityToDto(userrepo.save(UserEntityDtoUtil.dtoToEntity(u)));
		
		

	}

	// to update a user
	public User updateUser(UserDto newUser, Long id) {

		
		return userrepo.findById(id).map(users -> {
			users.setName(newUser.getName());
			users.setDesignation(newUser.getDesignation());
			return userrepo.save(users);
		})
				.orElseThrow( () -> 
//			newUser.setU_id(id);
//			return userrepo.save(UserEntityDtoUtil.dtoToEntity(newUser));
					  new UserException(+id+" Not Found"));
		
		
	}
	
	//to delete a user
	public void deleteById(Long id) {
		
		userrepo.findById(id).orElseThrow( () -> new UserException(+id+" Not Found"));
		userrepo.deleteById(id);
		
	}

//	private void exit() {
//		// TODO Auto-generated method stub
//
//	}

}