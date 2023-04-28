package project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.dto.PartnerContentDto;
import project.dto.PartnerDetailCommentDto;
import project.dto.PayListDto;
import project.dto.UserDto;

@Mapper
public interface BridgeMapper {
	
	List<PayListDto> selectPayList(int plIdx) throws Exception;
	List<PartnerContentDto> selectPartnerContent(int pdIdx) throws Exception;
	List<PartnerDetailCommentDto> selectPartnerComment(int pcIdx) throws Exception;
	int insertPartnerContent(PartnerContentDto partnerContentDto) throws Exception;
	int updatePartnerContent(PartnerContentDto partnerContentDto) throws Exception;
	int deletePartnerContent(int pcIdx) throws Exception;
	int insertPartnerComment(PartnerDetailCommentDto partnerDetailCommentDto) throws Exception;
	List<UserDto> selectUserId(UserDto userDto) throws Exception;
	int userIdCheck(String userId) throws Exception;
}
