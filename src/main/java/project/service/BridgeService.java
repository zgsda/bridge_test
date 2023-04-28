package project.service;

import java.util.List;

import project.dto.PartnerContentDto;
import project.dto.PartnerDetailCommentDto;
import project.dto.PayListDto;

public interface BridgeService {

	public List<PayListDto> selectPayList(int plIdx) throws Exception;
	public List<PartnerContentDto> selectPartnerContent(int pdIdx) throws Exception;
	public List<PartnerDetailCommentDto> selectPartnerComment(int pcIdx) throws Exception;
	public int insertPartnerContent(PartnerContentDto partnerContentDto) throws Exception;
	public int updatePartnerContent(PartnerContentDto partnerContentDto) throws Exception;
	public int deletePartnerContent(int pcIdx) throws Exception;
	public int insertPartnerComment(PartnerDetailCommentDto partnerDetailCommentDto) throws Exception;
//	public List<UserDto> selectUserId(UserDto userDto) throws Exception;
	public int userIdCheck(String userId) throws Exception;

}
