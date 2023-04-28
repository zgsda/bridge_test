package project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.dto.PartnerDetailCommentDto;
import project.dto.PartnerDetailDto;
import project.dto.PayListDto;

@Mapper
public interface BridgeMapper {
	
	// 1. 파트너 협업창 결제 내역
	List<PayListDto> selectPayList(PayListDto payListDto) throws Exception;
	
	// 2. 파트너 협업창 작업목록 조회
	List<PartnerDetailDto> selectProjectList(String userId1) throws Exception;
	// 3. 파트너 협업창 게시글의 댓글 조회
	List<PartnerDetailCommentDto> selectPartnerComment(int pcIdx) throws Exception;
}
