package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import project.dto.PartnerDetailCommentDto;
import project.dto.PartnerDetailDto;
import project.dto.PayListDto;
import project.mapper.BridgeMapper;

@Slf4j
@Service
public class BridgeServiceImpl implements BridgeService{

	@Autowired 
	private BridgeMapper bridgeMapper;
	
	// 파트너 협업창 결제 내역
	@Override
	public PayListDto selectPayList(PayListDto payListDto) throws Exception{
		return bridgeMapper.selectPayList(payListDto);
	}
	
	// 2. 파트너 협업창 작업목록 조회
	@Override
	public List<PartnerDetailDto> selectProjectList(String userId1) throws Exception {
		return bridgeMapper.selectProjectList(userId1);
	}
	
	// 3. 파트너 협업창 게시글의 댓글 조회
	@Override
	public List<PartnerDetailCommentDto> selectPartnerComment(int pcIdx) throws Exception{
		return bridgeMapper.selectPartnerComment(pcIdx);
	}
	
	// 4. 파트너 협업창 게시글의 댓글 작성
	@Override
	public int insertPartnerComment(PartnerDetailCommentDto partnerDetailCommentDto) throws Exception {
		return bridgeMapper.insertPartnerComment(partnerDetailCommentDto);
	}
	
}
