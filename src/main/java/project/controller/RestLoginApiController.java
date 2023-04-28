package project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import project.dto.KaKaoInformationDto;
import project.dto.UsersDto;
import project.security.JwtTokenUtil;
import project.service.LoginService;

@Slf4j
@RestController

public class RestLoginApiController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private KaKaoInformationDto kakaoInformationDto;
	private UsersDto usersDto;

	@PostMapping("/api/regist")
	public ResponseEntity<Object> regist(@RequestBody UsersDto userDto) throws Exception {
		int registedCount = loginService.registUser(userDto);
		if (registedCount > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(registedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(registedCount);
		}

	}
	

	// 외부 로그인
	@PostMapping("/api/bridge/pass/login")
	public ResponseEntity<UsersDto> loginPass(@RequestBody UsersDto usersDto) throws Exception {		

		UsersDto usersDto1 = new UsersDto();
		usersDto1 = loginService.passInformation(usersDto);
		String jwtToken = jwtTokenUtil.generateToken(usersDto);
		
		if(usersDto1 != null) {
		return ResponseEntity.status(HttpStatus.OK).header("token",jwtToken).body(usersDto1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
	

	
	
}
