package userpack.util;

import userpack.dto.UserDto;
import userpack.entity.User;

public class UserEntityDtoUtil {
	
	public static UserDto entityToDto(User u) {
		return new UserDto(u.getU_id(),u.getName(),u.getDesignation());
		
}
		public static User dtoToEntity(UserDto d) {
			
			return new User(d.getU_id(),d.getName(),d.getDesignation());
		}
		
	}
	
	

