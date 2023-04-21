package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;
import project.dto.PartnerCommentDto;
import project.dto.PartnerContentDto;
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
	public List<PartnerCommentDto> selectPartnerComment(int pcIdx) throws Exception{
		return bridgeMapper.selectPartnerComment(pcIdx);
	}
	@Override
	public void insertPartnerContent(PartnerContentDto PartnerContentDto) throws Exception {
		bridgeMapper.insertPartnerContent(PartnerContentDto);		
	}

	@Override
	public void updatePartnerContent(PartnerContentDto PartnerContentDto) throws Exception {
		bridgeMapper.updatePartnerContent(PartnerContentDto);		
	}

	@Override
	public void deletePartnerContent(int pcIdx) throws Exception {
		bridgeMapper.deletePartnerContent(pcIdx);
	}
}
