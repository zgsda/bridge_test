package project.service;

import java.util.List;

import project.dto.PartnerDetailCommentDto;
import project.dto.PartnerDetailDto;
import project.dto.PayListDto;

public interface BridgeService {

	// 파트너 협업창 결제 내역
	public PayListDto selectPayList(PayListDto payListDto) throws Exception;
	
	// 2. 파트너 협업창 작업목록 조회
	public List<PartnerDetailDto> selectProjectList(String userId1) throws Exception;
	
	// 3. 파트너 협업창 게시글의 댓글 조회
	public List<PartnerDetailCommentDto> selectPartnerComment(int pcIdx) throws Exception;
	
	// 4. 파트너 협업창 게시글의 댓글 작성
	public int insertPartnerComment(PartnerDetailCommentDto partnerDetailCommentDto) throws Exception;
}
