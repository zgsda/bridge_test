package project.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import project.dto.LoginDto;
import project.dto.UserDto;

public interface LoginService extends UserDetailsService {
	public UserDto login(LoginDto loginDto) throws Exception;
	public int registUser(UserDto userDto) throws Exception;
	
	
}
