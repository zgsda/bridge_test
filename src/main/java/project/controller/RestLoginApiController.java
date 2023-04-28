package project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.dto.UserDto;
import project.service.LoginService;

@RestController

public class RestLoginApiController {
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/api/regist")
	public ResponseEntity<Object> regist(@RequestBody UserDto userDto) throws Exception {
		System.out.println("aaaaaaaaaaaaaaaaaaaaaasda");
		int registedCount = loginService.registUser(userDto);
		if (registedCount > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(registedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(registedCount);
		}
		
	}
	//아이디 중복확인 
//	@PostMapping("/regist")
//	public ResponseEntity<String> registerUser(@RequestBody LoginDto loginDto) {
//	    String userId = loginDto.getUserId();
////	    String userPw = loginDto.getUserPw();
////
////	    if(!userPw.equals(loginDto.getUserPwConfirm())) {
////	        return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
////	    }
//	    boolean isDuplicated = LoginService.isIdDuplicated(userId);
//	    if(isDuplicated) {
//	        return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다.");
//	    }

	    // 회원가입 로직 처리

//	    return ResponseEntity.ok("회원가입이 완료되었습니다.");
//	}
//
}

 