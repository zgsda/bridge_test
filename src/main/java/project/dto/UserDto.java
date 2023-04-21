package project.dto;

import lombok.Data;

@Data
public class UserDto {
	private String userId;
	private String userPw;
	private String userName;
	private String userNickname;
	private String userPhoneNumber;
	private String userEmail;
	private int userPoint;
	private String userHalted;
	private int reportCount;
}
