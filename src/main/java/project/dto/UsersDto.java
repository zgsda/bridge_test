package project.dto;

import lombok.Data;

@Data
public class UsersDto {
	private String userId;
	private String userPw;
	private String userName;
	private String userNickName;
	private String userPhoneNumber;
	private String userEmail;
	private int userPoint;
	private String userHalted;
	private int reportCount;
}
