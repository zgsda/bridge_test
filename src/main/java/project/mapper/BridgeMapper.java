package project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import project.dto.PartnerCommentDto;
import project.dto.PartnerContentDto;
import project.dto.PayListDto;

@Mapper
public interface BridgeMapper {
	
	List<PayListDto> selectPayList(int plIdx) throws Exception;
	List<PartnerContentDto> selectPartnerContent(int pdIdx) throws Exception;
	List<PartnerCommentDto> selectPartnerComment(int pcidx) throws Exception;
	void insertPartnerContent(PartnerContentDto PartnerContentDto) throws Exception;
	void updatePartnerContent(PartnerContentDto PartnerContentDto) throws Exception;
	void deletePartnerContent(int pcIdx) throws Exception;
}
