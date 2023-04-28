package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import project.dto.PartnerContentDto;
import project.dto.PartnerDetailCommentDto;
import project.dto.PayListDto;
import project.mapper.BridgeMapper;

@Slf4j
@Service
public class BridgeServiceImpl implements BridgeService{

	@Autowired 
	private BridgeMapper bridgeMapper;
	
	@Override
	public List<PayListDto> selectPayList(int plIdx) throws Exception{
		return bridgeMapper.selectPayList(plIdx);
	}
		
	@Override
	public List<PartnerContentDto> selectPartnerContent(int pdIdx) throws Exception{
		return bridgeMapper.selectPartnerContent(pdIdx);
	}
	@Override
	public List<PartnerDetailCommentDto> selectPartnerComment(int pcIdx) throws Exception{
		return bridgeMapper.selectPartnerComment(pcIdx);
	}
	@Override
	public int insertPartnerContent(PartnerContentDto partnerContentDto) throws Exception {
		return bridgeMapper.insertPartnerContent(partnerContentDto);		
	}

	@Override
	public int updatePartnerContent(PartnerContentDto partnerContentDto) throws Exception {
	    return bridgeMapper.updatePartnerContent(partnerContentDto);	
	}

	@Override
	public int deletePartnerContent(int pcIdx) throws Exception {
		return bridgeMapper.deletePartnerContent(pcIdx);
	}
	
	@Override
	public int insertPartnerComment(PartnerDetailCommentDto partnerDetailCommentDto) throws Exception {
		return bridgeMapper.insertPartnerComment(partnerDetailCommentDto);		
	}
//	@Override
//	public List<UserDto> selectUserId(UserDto userDto) throws Exception {
//		return bridgeMapper.selectUserId(userDto);
//	}
	
	@Override
	public int userIdCheck(String userIdCheck) throws Exception {
		int result = bridgeMapper.userIdCheck(userIdCheck);
		return result;
	}
}
